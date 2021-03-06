import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Grid, Row, Col, Table, DropdownButton} from 'react-bootstrap';
import {Card, CardHeader, CardText} from 'material-ui/Card';
import TextField from 'material-ui/TextField';
import DatePicker from 'material-ui/DatePicker';
import SelectField from 'material-ui/SelectField';
import AutoComplete from 'material-ui/AutoComplete';
import MenuItem from 'material-ui/MenuItem';
import Dialog from 'material-ui/Dialog';
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import Api from '../../../../../api/api';
import DataTable from '../../../../common/Table';
import {translate} from '../../../../common/common';

import $ from 'jquery';
import 'datatables.net-buttons/js/buttons.html5.js';// HTML 5 file export
import 'datatables.net-buttons/js/buttons.flash.js';// Flash file export
import jszip from 'jszip/dist/jszip';
import pdfMake from "pdfmake/build/pdfmake";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;

var _this;
var flag = 0;

const getNameById = function(object, id, property = "") {
  if (id == "" || id == null) {
        return "";
    }
    for (var i = 0; i < object.length; i++) {
        if (property == "") {
            if (object[i].id == id) {
                return object[i].name;
            }
        } else {
            if (object[i].hasOwnProperty(property)) {
                if (object[i].id == id) {
                    return object[i][property];
                }
            } else {
                return "";
            }
        }
    }
    return "";
}
const getNameByBoundary = function(object, id) {
  if (id == "" || id == null) {
        return "";
    }
    for (var i = 0; i < object.length; i++) {
            if (object[i].id == id) {
                return object[i].boundaryType.name;
            }
        }
}

const styles = {
  headerStyle : {
    fontSize : 19
  },
  marginStyle:{
    margin: '15px'
  }
};

var flag = 0;
class searchRouter extends Component {
  constructor(props) {
      super(props);
      this.state = {
          allSourceConfig: {
            text: 'name',
            value: 'id'
          },
          complaintSourceConfig: {
            text: 'serviceName',
            value: 'serviceCode'
          },
          complaintSource: [],
          boundarySource: [],
          boundaryTypeList: [],
          isSearchClicked: false,
          resultList: [],
          boundaryInitialList: [],
          positionSource:[]
       }
      this.loadBoundaries = this.loadBoundaries.bind(this);
      this.search = this.search.bind(this);
      this.handleNavigation = this.handleNavigation.bind(this);
      this.setInitialState = this.setInitialState.bind(this);
  }

  setInitialState(_state) {
    this.setState(_state);
  }

  componentWillUpdate() {
    if(flag == 1) {
      flag = 0;
      $('#searchTable').dataTable().fnDestroy();
    }
  }

  componentWillMount() {
  }

  componentWillUnmount(){
     $('#searchTable')
     .DataTable()
     .destroy(true);
  }

  componentDidUpdate() {

    var t = $('#searchTable').DataTable({
      dom:'<"col-md-4"l><"col-md-4"B><"col-md-4"f>rtip',
      buttons: ['excel', 'pdf'],
      bDestroy: true
    });

    t.on( 'order.dt search.dt', function () {
        t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
  }

  componentDidMount() {
    var self = this, count = 4, _state = {};
    const checkCountAndCall = function(key, res) {
      _state[key] = res;
      count--;
      if(count == 0) {
        self.setInitialState(_state);
        self.props.setLoadingStatus("hide");
      }
    }

    this.props.initForm();
    self.props.setLoadingStatus("loading");
    Api.commonApiPost("egov-location/boundarytypes/getByHierarchyType", {hierarchyTypeName: "ADMINISTRATION"}).then(function(response) {
        checkCountAndCall("boundaryTypeList", response.BoundaryType);
    }, function(err) {
        checkCountAndCall("boundaryTypeList", []);
    });

    Api.commonApiGet("/egov-location/boundarys", {"Boundary.tenantId": localStorage.getItem("tenantId")}).then(function(response) {
        checkCountAndCall("boundaryInitialList", response.Boundary);
    }, function(err) {
        checkCountAndCall("boundaryInitialList", []);
    });
    Api.commonApiPost("/hr-masters/positions/_search").then(function(response) {
      checkCountAndCall("positionSource", response.Position);
    }, function(err) {
        checkCountAndCall("positionSource", []);
    });

    Api.commonApiPost("/pgr-master/service/v1/_search", {type:'all', keywords : 'complaint'}).then(function(response) {
       checkCountAndCall("complaintSource", response.Service);
    },function(err) {
       checkCountAndCall("complaintSource", []);
    });
  }

  loadBoundaries(value) {
     var self = this;
     Api.commonApiPost("/egov-location/boundarys/getByBoundaryType", {"boundaryTypeId": value, "Boundary.tenantId": localStorage.getItem("tenantId")}).then(function(response) {
       self.setState({boundarySource : response.Boundary});
     },function(err) {

     });
  }

  search(e) {
    e.preventDefault();
    var self = this;
    var searchSet = Object.assign({}, self.props.routerSearchSet);
    if(!searchSet.serviceid)
      delete searchSet.serviceid;
    if(!searchSet.boundaryid)
      delete searchSet.boundaryid;
    if(searchSet.boundaryType)
      delete searchSet.boundaryType;
    self.props.setLoadingStatus("loading");
    Api.commonApiPost("/workflow/router/v1/_search", searchSet).then(function(response) {
      flag = 1;
      self.setState({
        resultList: response.RouterTypRes,
        isSearchClicked: true
      });
      self.props.setLoadingStatus("hide");
    }, function(err) {
      self.props.toggleSnackbarAndSetText(true, err.message);
      self.props.setLoadingStatus("hide");
    })
  }

  handleNavigation(id) {
    this.props.history.push("/pgr/createRouter/" + this.props.match.params.type + "/" + id);
  }

  render() {
    _this = this;
    let {
      routerSearchSet,
      handleAutoCompleteKeyUp,
      handleChange
    } = this.props;
    let {
      loadBoundaries,
      search,
      handleNavigation
    } = this;
    let {
        allSourceConfig,
        complaintSourceConfig,
        complaintSource,
        boundarySource,
        boundaryTypeList,
        open,
        resultList,
        isSearchClicked,
        boundaryInitialList,
        positionSource
    } = this.state;

    const renderBody = function() {
      if(resultList && resultList.length)
      return resultList.map(function(val, i) {
        return (
          <tr key={i} onClick={() => {handleNavigation(val.id)}}>
            <td>{i+1}</td>
            <td>{val.service ? val.service.serviceName : ""}</td>
            <td>{getNameByBoundary(boundaryInitialList, val.boundary.boundaryType)}</td>
            <td>{getNameById(boundaryInitialList, val.boundary.boundaryType)}</td>
            <td>{getNameById(positionSource, val.position)}</td>
          </tr>
        )
      })
   }

   const viewTable = function() {
      if(isSearchClicked)
      return (
          <Card style={styles.marginStyle}>
            <CardHeader title={<strong style = {{color:"#5a3e1b"}} > {translate("pgr.searchresult")} </strong>}/>
            <CardText>
              <Table id="searchTable" style={{color:"black",fontWeight: "normal"}} bordered responsive className="table-striped">
               <thead>
                  <tr>
                    <th>#</th>
                    <th>{translate("pgr.lbl.grievance.type")}</th>
                    <th>{translate("pgr.lbl.boundarytype")}</th>
                    <th>{translate("pgr.lbl.boundary")}</th>
                    <th>{translate("pgr.lbl.position")}</th>
                  </tr>
                </thead>
                <tbody>
                  {renderBody()}
                </tbody>
              </Table>
           </CardText>
        </Card>
    )
   }

    return (
      <div className="searchRouter">
         <form autoComplete="off" onSubmit={(e) => {search(e)}}>
           <Card style={styles.marginStyle}>
            <CardHeader style={{paddingBottom:0}} title={<div style = {styles.headerStyle} > {translate('core.lbl.search')} {translate('pgr.lbl.router')}</div>}/>
              <CardText style={{padding:0}}>
                 <Grid>
                   <Row>
                   <Col xs={12} sm={6} md={6} lg={6}>
                    <AutoComplete
                        hintText=""
                        floatingLabelText={translate("pgr.lbl.grievance.type")}
                        filter={AutoComplete.caseInsensitiveFilter}
                        fullWidth={true}
                        dataSource={this.state.complaintSource}
                        dataSourceConfig={this.state.complaintSourceConfig}
                        menuStyle={{overflow:'auto', maxHeight: '150px'}}  listStyle={{overflow:'auto'}}
                        onKeyUp={(e) => {handleAutoCompleteKeyUp(e, "serviceid")}}
                        value={routerSearchSet.serviceid}
                        ref="serviceid"
                        onNewRequest={(chosenRequest, index) => {
                          if(index === -1){
                            this.refs['serviceid'].setState({searchText:''});
                          }else{
                            var e = {
                              target: {
                                value: chosenRequest.id
                              }
                            };
                            handleChange(e, "serviceid", true, "");
                          }

                         }}
                        />
                   </Col>
                   <Col xs={12} sm={6} md={6} lg={6}>
                    <SelectField maxHeight={200} fullWidth={true} floatingLabelText={translate("pgr.lbl.boundarytype")} value={routerSearchSet.boundaryType} onChange={(e, i, val) => {
                            var e = {target: {value: val}};
                            loadBoundaries(val);
                            handleChange(e, "boundaryType", true, "")}}>
                            {boundaryTypeList.map((item, index) => (
                                      <MenuItem value={item.id} key={index} primaryText={item.name} />
                                  ))}
                     </SelectField>
                   </Col>
                   <Col xs={12} sm={6} md={6} lg={6}>
                    <AutoComplete
                        hintText=""
                        floatingLabelText={translate("pgr.lbl.boundary")}
                        filter={AutoComplete.caseInsensitiveFilter}
                        fullWidth={true}
                        dataSource={this.state.boundarySource}
                        dataSourceConfig={this.state.allSourceConfig}
                        menuStyle={{overflow:'auto', maxHeight: '150px'}}  listStyle={{overflow:'auto'}}
                        onKeyUp={(e) => {handleAutoCompleteKeyUp(e, "boundaryid")}}
                        value={routerSearchSet.boundaryid}
                        ref="boundaryid"
                        onNewRequest={(chosenRequest, index) => {
                          if(index === -1){
                            this.refs['boundaryid'].setState({searchText:''});
                          }else{
                            var e = {
                              target: {
                                value: chosenRequest.id
                              }
                            };
                            handleChange(e, "boundaryid", true, "");
                          }

                         }}
                        />
                   </Col>
                  </Row>
                 </Grid>
              </CardText>
           </Card>
           <div style={{textAlign: 'center'}}>
             <RaisedButton style={{margin:'15px 5px'}} type="submit" label={translate("core.lbl.search")} primary={true}/>
           </div>
           {viewTable()}
         </form>
        </div>
    );
  }
}

const mapStateToProps = state => {
  return ({routerSearchSet: state.form.form});
};
const mapDispatchToProps = dispatch => ({
  initForm: (type) => {
        dispatch({
        type: "RESET_STATE",
        validationData: {
          required: {
            current: [],
            required: []
          },
          pattern: {
            current: [],
            required: []
          }
        }
      });
    },
    handleChange: (e, property, isRequired, pattern) => {
      dispatch({type: "HANDLE_CHANGE", property, value: e.target.value, isRequired, pattern});
  },
  handleAutoCompleteKeyUp : (e, type) => {
      var self = _this;
      dispatch({type: "HANDLE_CHANGE", property: type, value: '', isRequired : true, pattern: ''});
  },
  setLoadingStatus: (loadingStatus) => {
    dispatch({type: "SET_LOADING_STATUS", loadingStatus});
  },
  toggleSnackbarAndSetText: (snackbarState, toastMsg) => {
    dispatch({type: "TOGGLE_SNACKBAR_AND_SET_TEXT", snackbarState, toastMsg});
  }
});

export default connect(mapStateToProps, mapDispatchToProps)(searchRouter);
