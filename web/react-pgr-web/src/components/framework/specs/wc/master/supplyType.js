var dat = {
	"wc.create": {
		"numCols": 12/3,
		"url": "/wcms/masters/supplytype/_create",
		"tenantIdRequired": true,
		"idJsonPath": "SupplyTypes[0].code",
		"objectName": "SupplyType",
		"groups": [
			{
				"label": "wc.create.supplyType.title",
				"name": "createSupplyType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "SupplyType[0].name",
							"label": "Supply Type",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 100"
						},
						{
							"name": "description",
							"jsonPath": "SupplyType[0].description",
							"label": "Description",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,250}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 250"
						},
						{
							"name": "Active",
							"jsonPath": "SupplyType[0].active",
							"label": "Active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"defaultValue":true,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"wc.search": {
		"numCols": 12/3,
		"url": "/wcms/masters/supplytype/_search",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "SupplyType",
		"groups": [
			{
				"label": "wc.search.supplyType.title",
				"name": "searchSupplyType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "name",
							"label": "wc.create.supplyType",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 100"
						},
						{
							"name": "Active",
							"jsonPath": "active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		],
		"result": {
			"header": [{label: "wc.create.code"},{label: "wc.create.supplyType"}, {label: "wc.search.result.description"}, {label: "wc.search.result.active"}],
			"values": ["code","name", "description", "active"],
			"resultPath": "SupplyTypes",
			"rowClickUrlUpdate": "/update/wc/supplyType/{id}",
			"rowClickUrlView": "/view/wc/supplyType/{id}"
			}
	},
	"wc.view": {
		"numCols": 12/3,
		"url": "/wcms/masters/supplytype/_search?id={id}",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "SupplyType",
		"groups": [
			{
				"label": "wc.view.supplytype.title",
				"name": "viewSupplyType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "SupplyTypes[0].name",
							"label": "wc.create.supplyType",
							"pattern": "",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "description",
							"jsonPath": "SupplyTypes[0].description",
							"label": "wc.create.description",
							"pattern": "",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "Active",
							"jsonPath": "SupplyTypes[0].active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"wc.update": {
		"numCols": 12/3,
		"searchUrl": "/wcms/masters/supplytype/_search?id={id}",
		"url":"/wcms/masters/supplytype/{SupplyTypes.id}/_update",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "SupplyTypes",
		"groups": [
			{
				"label": "wc.update.supplyType.title",
				"name": "updateSupplyType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "SupplyTypes[0].name",
							"label": "wc.create.supplyType",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 100"
						},
						{
							"name": "description",
							"jsonPath": "SupplyTypes[0].description",
							"label": "wc.create.description",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,250}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 250"
						},
						{
							"name": "Active",
							"jsonPath": "SupplyTypes[0].active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	}
}

export default dat;
