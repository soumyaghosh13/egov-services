import React, {Component} from 'react';
import {Grid, Row, Col, Table, DropdownButton} from 'react-bootstrap';
import {Card, CardHeader, CardText} from 'material-ui/Card';
import {translate} from '../../common/common';

const $ = require('jquery');
$.DataTable = require('datatables.net');
const dt = require('datatables.net-bs');

export default class UiTable extends Component {
	constructor(props) {
       super(props);
   	}

   	componentWillMount() {
	    $('#searchTable').DataTable({
	       dom: 'lBfrtip',
	       buttons: [],
	       bDestroy: true,
	       language: {
	           "emptyTable": "No Records"
	       }
	    });
   	}

   	componentWillUnmount() {
    	$('#searchTable').DataTable().destroy(true);
  	}

  	componentWillUpdate() {
	    $('#searchTable').dataTable().fnDestroy();
	}

	componentDidUpdate() {
	    $('#searchTable').DataTable({
	         dom: 'lBfrtip',
	         buttons: [],
	          ordering: false,
	          bDestroy: true,
	          language: {
	             "emptyTable": "No Records"
	          }
	    });
  	}

  	render() {
  		let {resultList} = this.props;

  		const renderTable = function () {
  			return (
  				<Card>
		          <CardHeader title={<strong> {translate("ui.table.title")} </strong>}/>
		          <CardText>
		          <Table id="searchTable" bordered responsive>
		          <thead>
		            <tr>
		              {resultList.resultHeader && resultList.resultHeader.length && reportResult.resultHeader.map((item, i) => {
		                return (
		                  <th key={i}>{translate(item.label)}</th>
		                )
		              })}

		            </tr>
		          </thead>
		          <tbody>

		                {resultList.hasOwnProperty("resultValues") && resultList.resultValues.map((item, i) => {
		                  return (
		                    <tr key={i}>
		                      {
		                      	item.map((item2, i2)=>{
			                        return (
			                          <td key={i2}>{item2}</td>
			                        )
		                      })}
		                    </tr>
		                    )

		                })}


		          </tbody>
		        </Table>
		      </CardText>
		      </Card>
  			)
  		}

  		return (
  			<div>
  				{this.props.resultList && renderTable()}
  			</div>
  		)
  	}
}