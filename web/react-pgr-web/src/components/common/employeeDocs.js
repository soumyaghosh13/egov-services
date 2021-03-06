import React, {Component} from 'react';
import {Grid, Row, Col, DropdownButton} from 'react-bootstrap';
import {Card, CardHeader, CardText} from 'material-ui/Card';
import RaisedButton from 'material-ui/RaisedButton';
import FileDownload from 'material-ui/svg-icons/action/get-app';
import {translate} from './common';

const styles = {
  headerStyle : {
    fontSize : 19
  },
  addBorderBottom:{
    borderBottom: '1px solid #eee',
    padding: '10px'
  },
  marginStyle:{
    margin: '15px'
  }
};

class employeeDocs extends Component{
  constructor(props){
    super(props);
    this.state = {
      docs : [],
      available : false
    }
  }
  componentWillReceiveProps(nextProps){
    if(nextProps.srn && nextProps.srn[0].attribValues){
      var docsarray = [];
      nextProps.srn[0].attribValues.map((attrib, index) => {
        if(attrib['key'].indexOf('employeeDocs') !== -1){
          docsarray.push(attrib);
        }
      });
      this.setState({docs : docsarray});
    }
  }
  docs = () =>{
    return this.state.docs.map((attrib, index) => {
        if(attrib['key'].indexOf('employeeDocs') !== -1){
          let key = (attrib['key'].split(/_(.+)/)[1]).length > 15 ? (attrib['key'].split(/_(.+)/)[1]).substr(0, 12)+'...' : attrib['key'].split(/_(.+)/)[1];
          return (
            <Col xs={6} md={3} key={index}>
              <RaisedButton
                href={'/filestore/v1/files/id?fileStoreId=' + attrib['name']+'&tenantId='+localStorage.getItem('tenantId')}
                download
                label={key}
                primary={true}
                fullWidth = {true}
                style={styles.marginStyle}
                labelPosition="before"
                icon={<FileDownload />}
              />
            </Col>
          )
        }
    });
  }
  render(){
    return(
      <div>
        {this.state.docs != undefined && this.state.docs.length > 0 ?
            <Grid style={{width:'100%'}}>
              <Card style={{margin:'15px 0'}}>
              <CardHeader style={{paddingBottom:0}} title={< div style = {styles.headerStyle} >
               {translate('core.documents')}
              < /div>}/>
              <CardText style={{padding:'8px 16px 0'}}>
                <Row>
                  {this.docs()}
                </Row>
              </CardText>
              </Card>
            </Grid>
          : ""
        }
      </div>
    )
  }
}

export default employeeDocs;
