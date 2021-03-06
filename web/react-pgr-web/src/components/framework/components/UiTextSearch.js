import React, {Component} from 'react';
import TextField from 'material-ui/TextField';

export default class UiTextSearch extends Component {
	constructor(props) {
       super(props);
   	}

	renderTextSearch = (item) => {
		switch (this.props.ui) {
			case 'google': 
				return (
					<div style={{position: 'relative', display: 'inline'}}>
						<TextField 
							onBlur={(ev) => this.props.autoComHandler(item.autoCompleteDependancy, item.jsonPath)}
							style={{"display": (item.hide ? 'none' : 'inline-block')}}
							errorStyle={{"float":"left"}}
							fullWidth={true} 
							floatingLabelText={item.label + (item.isRequired ? " *" : "")} 
							value={this.props.getVal(item.jsonPath)}
							disabled={item.isDisabled}
							errorText={this.props.fieldErrors[item.jsonPath]}
							onChange={(e) => this.props.handler(e, item.jsonPath, item.isRequired ? true : false, item.pattern, item.requiredErrMsg, item.patternErrMsg)} />
						<span className="glyphicon glyphicon-search" style={{"position":"absolute", "right": "10px", "display": (item.display ? 'none' : '')}} onClick={() => this.props.autoComHandler(item.autoCompleteDependancy, item.jsonPath)}></span>
					</div>
				);
		}
	}

	render () {
		return (
	      <div>
	        {this.renderTextSearch(this.props.item)}
	      </div>
	    );
	}
}