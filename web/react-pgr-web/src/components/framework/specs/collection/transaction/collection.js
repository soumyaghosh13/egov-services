var cashOrMops = {
  "name": "FloorDetailsComponent",
  "version": "v1", //Maps to parent version
  "level": 1,
  "jsonPath": "collection.modeOfPayment",
  "groups": [{
    "label": "Cash",
    "name": "cash",
    "multiple": false, //If true, its an array
    "children": [],
    "fields": [{
      "name": "paidBy",
      "jsonPath": "collection.modeOfPayment.paidBy",
      "label": "Paid By",
      "pattern": "",
      "type": "text",
      "isRequired": true,
      "isDisabled": false,
      "requiredErrMsg": "", //Remove required messages
      "patternErrMsg": ""
    }]
  }]
}

var chequeOrDD = {
  "name": "chequeOrDD",
  "version": "v1", //Maps to parent version
  "level": 1,
  "jsonPath": "collection.modeOfPayment",
  "groups": [{
    "label": "Cheque Or DD",
    "name": "chequeOrDD",
    "multiple": false, //If true, its an array
    "children": [],
    "hide": false,
    "fields": [{
        "name": "chequeOrDDNumber",
        "jsonPath": "Receipt[0].instrument.transactionNumber",
        "label": "Cheque/DD Number",
        "pattern": "^[0-9]{6}$",
        "type": "text",
        "isRequired": true,
        "isDisabled": false,
        "requiredErrMsg": "",
        "patternErrMsg": "Cheque/DD number should be 6 digit numeric number"
      },
      {
        "name": "chequeOrDDDate",
        "jsonPath": "Receipt[0].instrument.transactionDateInput",
        "label": "Cheque/DD Date",
        "pattern": "/^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/",
        "type": "datePicker",
        "isRequired": true,
        "isDisabled": false,
        "requiredErrMsg": "",
        "patternErrMsg": "Cheque/DD date should accept up-to previous 90 days or current date"
      },
      {
        "name": "chequeOrDDBankName",
        "jsonPath": "Receipt[0].instrument.bank.id",
        "label": "Cheque/DD Bank Name",
        "pattern": "",
        "type": "autoCompelete",
        "isRequired": true,
        "isDisabled": false,
        "requiredErrMsg": "",
        "patternErrMsg": "",
        "url": "egf-masters/banks/_search?|$..id|$..name"
      },
      {
        "name": "chequeOrDDBranchName",
        "jsonPath": "Receipt[0].instrument.branchName",
        "label": "Cheque/DD Branch Name",
        "pattern": "",
        "type": "text",
        "isRequired": false,
        "isDisabled": false,
        "requiredErrMsg": "",
        "patternErrMsg": ""
      }
    ]
  }]
}

// var creditOrDebitCard={
//   "name": "FloorDetailsComponent",
//   "version": "v1", //Maps to parent version
//   "level": 1,
//   "jsonPath": "connection.floors",
//   "groups": [{
//     "label": "wc.create.groups.floorDetails.title",
//     "name": "FloorDetails",
//     "multiple": false, //If true, its an array
//     "children": [],
//     "fields": [{
//         "name": "FloorNo",
//         "jsonPath": "connection.floors[0].floorNo",
//         "label": "wc.create.groups.floorDetails.floorNo",
//         "pattern": "",
//         "type": "number",
//         "isRequired": true,
//         "isDisabled": false,
//         "requiredErrMsg": "", //Remove required messages
//         "patternErrMsg": ""
//       },
//       {
//         "name": "FloorName",
//         "jsonPath": "connection.floors[0].floorName",
//         "label": "wc.create.groups.floorDetails.floorName",
//         "pattern": "",
//         "type": "text",
//         "isRequired": false,
//         "isDisabled": false,
//         "requiredErrMsg": "", //Remove required messages
//         "patternErrMsg": ""
//       }
//     ]
//   }]
// }

// var directBank={
//   "name": "FloorDetailsComponent",
//   "version": "v1", //Maps to parent version
//   "level": 1,
//   "jsonPath": "connection.floors",
//   "groups": [{
//     "label": "wc.create.groups.floorDetails.title",
//     "name": "FloorDetails",
//     "multiple": false, //If true, its an array
//     "children": [],
//     "fields": [{
//         "name": "FloorNo",
//         "jsonPath": "connection.floors[0].floorNo",
//         "label": "wc.create.groups.floorDetails.floorNo",
//         "pattern": "",
//         "type": "number",
//         "isRequired": true,
//         "isDisabled": false,
//         "requiredErrMsg": "", //Remove required messages
//         "patternErrMsg": ""
//       },
//       {
//         "name": "FloorName",
//         "jsonPath": "connection.floors[0].floorName",
//         "label": "wc.create.groups.floorDetails.floorName",
//         "pattern": "",
//         "type": "text",
//         "isRequired": false,
//         "isDisabled": false,
//         "requiredErrMsg": "", //Remove required messages
//         "patternErrMsg": ""
//       }
//     ]
//   }]
// }

var dat = {
  "collection.transaction": {
    "numCols": 3,
    "url": "/billing-service/bill/_generate",
    "tenantIdRequired": true,
    "objectName": "Collection",
    "useTimestamp": true,
    "groups": [{
      "label": "Pay tax",
      "name": "createDocumentType",
      "fields": [
        {
          "name": "mobile",
          "jsonPath": "mobileNumber",
          "label": "Mobile",
          "pattern": "^.{10,10}$",
          "type": "number",
          "isRequired": false,
          "isDisabled": false,
          "requiredErrMsg": "",
          "patternErrMsg": "Mobile Number must be of 10 digits"
        },
        {
          "name": "email",
          "jsonPath": "email",
          "label": "Email",
          "pattern": "^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+$",
          "type": "text",
          "isRequired": false,
          "isDisabled": false,
          "requiredErrMsg": "",
          "patternErrMsg": "Enter Valid EmailID"
        },
        {
          "name": "businessService",
          "jsonPath": "businessService",
          "label": "Billing service name",
          "pattern": "",
          "type": "singleValueList",
          "isRequired": false,
          "isDisabled": false,
          "url": "/egov-common-masters/businessDetails/_search?tenantId=default|$..code|$..name",
          "requiredErrMsg": "",
          "patternErrMsg": ""
        },
        {
          "name": "consumerCode",
          "jsonPath": "consumerCode",
          "label": "Consumer Code",
          "pattern": "^.{0,30}$",
          "type": "text",
          "isRequired": false,
          "isDisabled": false,
          "requiredErrMsg": "",
          "patternErrMsg": "Enter valid Consumer Code"
        }
      ]
    }],
    "result": {
      "header": [{
          "name": "businessService",
          "jsonPath": "businessService",
          "label": "Biller Service Name",
          "pattern": "",
          "type": "label",
          "isRequired": true,
          "isDisabled": false,
          "requiredErrMsg": "",
          "patternErrMsg": "",
          "isLabel": false
        },
        {
          "name": "consumerCode",
          "jsonPath": "consumerCode",
          "label": "Consumer code",
          "pattern": "",
          "type": "label",
          "isRequired": true,
          "isDisabled": false,
          "requiredErrMsg": "",
          "patternErrMsg": "",
          "isLabel": false
        },
        {
          "name": "totalAmount",
          "jsonPath": "totalAmount",
          "label": "Amount Due",
          "pattern": "",
          "type": "label",
          "isRequired": true,
          "isDisabled": false,
          "requiredErrMsg": "",
          "patternErrMsg": "",
          "isLabel": false
        },
        {
          "name": "minimumAmount",
          "jsonPath": "minimumAmount",
          "label": "Minimum amount payable",
          "pattern": "",
          "type": "label",
          "isRequired": true,
          "isDisabled": false,
          "requiredErrMsg": "",
          "patternErrMsg": "",
          "isLabel": false
        },
        {
          "name": "amountPaid",
          "jsonPath": "amountPaid",
          "label": "Amount paid(Rs)",
          "pattern": "^\\d+(\\.\\d+)?$",
          "type": "text",
          "isRequired": true,
          "isDisabled": false,
          "requiredErrMsg": "Amount paid should be above minimum amount",
          "patternErrMsg": "",
          "isLabel": false
        }
      ],
      // "values": ["businessService", "consumerCode", "totalAmount","minimumAmount","bill"],
      "resultPath": "Bill[0].billDetails",
      "tableResultPath": "Receipt[0].Bill[0].billDetails",

      // "rowClickUrlUpdate": "/update/wc/pipeSize/{id}",
      // "rowClickUrlView": "/view/wc/pipeSize/{id}"
    },
    "transaction": [{
      "label": "Payment",
      "name": "paymentMode",
      "children": [chequeOrDD],
      "fields": [
        // {
        // 	"name": "totalAmountPaid",
        // 	"jsonPath": "Receipt[0].instrument.amount",
        // 	"label": "Total Amount Paid",
        // 	"pattern": "",
        // 	"type": "label",
        // 	"isRequired": false,
        // 	"isDisabled": false,
        // 	"isHidden": false,
        // 	"defaultValue": "",
        // 	"requiredErrMsg": "",
        // 	"patternErrMsg": ""
        // },
        {
          "name": "modeOfPayment",
          "jsonPath": "Receipt[0].instrument.instrumentType.name",
          "label": "Mode Of Payment",
          "pattern": "",
          "type": "singleValueList",
          "isRequired": true,
          "isDisabled": false,
          "url": "",
          "requiredErrMsg": "",
          "patternErrMsg": "",
          // "showHideFields": [{
          //   "ifValue": "Cheque",
          //   "hide": [],
          //   "show": [{
          //     "name": "chequeOrDD",
          //     "isGroup": true,
          //     "isField": false
          //   }]
          // }],

          "defaultValue": [
            {
              "key": "Cash",
              "value": "Cash"
            },
            {
              "key": "Cheque",
              "value": "Cheque"
            },
            {
              "key": "DD",
              "value": "DD"
            },
            // {
            //   "key": "4",
            //   "value": "Credit/Debit Card"
            // },
            // {
            //   "key": "5",
            //   "value": "Direct Bank"
            // },
            // {
            //   "key": "6",
            //   "value": "SBI MOPS Bank Callan"
            // }
          ]
        },
        {
          "name": "paidBy",
          "jsonPath": "Receipt[0].Bill[0].paidBy",
          "label": "Paid By",
          "pattern": "",
          "type": "text",
          "isRequired": true,
          "isDisabled": false,
          "requiredErrMsg": "", //Remove required messages
          "patternErrMsg": ""
        }
      ]
    }]
  }
}





export default dat;
