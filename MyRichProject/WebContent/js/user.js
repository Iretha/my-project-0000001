/**
 * fieldType = 'input' за бутон
 * 
 * @param fieldType
 * @param endsWithId
 * @return fullId
 */
function func_getFieldIdByType(fieldType, endsWithId) {

	var list = document.getElementsByTagName(fieldType);

	for ( var idx = 0; idx < list.length; idx++) {
		var tmp = list[idx].id;
		if (tmp.match(endsWithId + "$")) {
			return tmp;
		}
	}
	return "";
}
 
 /**
	 * Клика бутон
	 * 
	 * @param btnId -
	 *            ид на бутона
	 * @return true/false
	 */
 function clickHiddenBtn(btnId) {
	  alert(btnId);
 	btn = document.getElementById(func_getFieldIdByType('input', btnId));
 	if (btn) {
 		btn.click();
 	 alert(btnId + ' Clicked!');
 		return true;
 	}
 	alert('Btn not found!');
 	return false;
 }
  
 /**
	 * 
	 * @param e
	 * @param onEnterBtnId
	 * @return true/false
	 */
function clickOnEnter(e, onEnterBtnId){
	  var key;
	  var btnToClick;
     if(window.event){
         key = window.event.keyCode;     // IE
     } else{
         key = e.which;     // firefox
     }
     if (key == 13){
  	  // alert(key + ":" + onEnterBtnId);
  	   btnToClick = document.getElementById(func_getFieldIdByType('input', onEnterBtnId));
   		 if (btnToClick) {
   			 btnToClick.click();
   			 return true;
   		 }
     }
	return false;
}
 
   
   /**
	 * 
	 * @param e
	 * @param onEnterBtnId
	 * @param newBtnId
	 * @return true/false
	 */
   function doClicks(e, onEnterBtnId, newBtnId){
 	   var key;
 	   var btnToClick;
       if(window.event){
           key = window.event.keyCode;     // IE
       } else{
           key = e.which;     // firefox
       }
       
       alert(key);
       
       if (key == 13){
    	  // alert(key + ":" + searchBtnId);
    	   btnToClick = document.getElementById(func_getFieldIdByType('input', onEnterBtnId));
		 if (btnToClick) {
			 btnToClick.click();
			 // alert(btnId + ' Clicked!');
			 return true;
		 }
       }else if(key == 10){
    	   alert(key + ":" + newBtnId);
    	   btnToClick = document.getElementById(func_getFieldIdByType('input', newBtnId));
  		 if (btnToClick) {
  			btnToClick.click();
  			 // alert(btnId + ' Clicked!');
  			 return true;
  		 }
       }
       return false;
   }
  
    /**
	 * 
	 * @param btnIdToSet
	 * @return true/false
	 */
  function setHiddenBtnSearchId(btnIdToSet){
	  var hiddenVal = document.getElementById(func_getFieldIdByType('input', 'hiddenBtnSearchId'));
	 // alert(hiddenVal);
	  if(hiddenVal){
		  hiddenVal.value = btnIdToSet;
		 // alert(hiddenVal.value);
		  return true;
	  }
	  
	  return false;
  }
     
 /**
	 * 
	 * @param e
	 * @param hiddenBtnFiledId
	 * @return true/false
	 */
 function doSearchOnEnterInCtx(e){
	  var key;
	  var btnToClick;
      if(window.event){
          key = window.event.keyCode;     // IE
      } else{
          key = e.which;     // firefox
      }
      if (key == 13){
    	var hiddenField = document.getElementById(func_getFieldIdByType('input', 'hiddenBtnSearchId'));
    	 if(hiddenField){
    		 // alert(hiddenField.value);
         	   btnToClick = document.getElementById(func_getFieldIdByType('input', hiddenField.value));
    	   		 if (btnToClick) {
    	   			 btnToClick.click();
    	   			 return true;
    	   		 }
    	 }
      }
	return false;
}
      
  /**
	 * 
	 * 
	 */
  function changeDsbldButtonsStyle(){
      var list = document.getElementsByTagName('input');
      for ( var idx = 0; idx < list.length; idx++) {
    	  if(list[idx].getAttribute('type') == 'button' || list[idx].getAttribute('type') == 'submit'){
    		  if(list[idx].disabled == true && list[idx].className != 'hide'){
              list[idx].className = 'disabledCommandButton';
    		  }
    	  }
      }
  }

   /**
	 * Избран KO
	 * 
	 * @param selectedRow
	 * @param dlgId
	 * @param msg
	 */
   function isKOSelected(selectedRow, dlgId, msg) {
   	if (selectedRow > -1) {
   		Richfaces.showModalPanel(dlgId);
   	} else {
   		alert(msg);
   	}
   }  
    
    function isEmptyField (fieldId){
    	var text = document.getElementById(func_getFieldIdByType('input', fieldId));
    	if(text != null && (text.value == null || text.value == '')){
    		return true;
    	}
    	return false;
    }
    
    
    function checkRequestedField(fieldId, msg){
    	if(isEmptyField(fieldId)){
    		alert(msg);
    		return false;
    	}
    	return true;
    }
    
    /**
	 * Валидира дължината на въведeното в текстово поле.
	 * 
	 * @param e
	 * @param elem
	 * @param maxsize
	 * @return true/false
	 * 
	 */
    function validateTALength (elem, maxsize, e) {
    	if(window.event){
            key = window.event.keyCode; // IE
        } else{
            key = e.which;  // firefox
        }
    
    	if (elem.value.length >= maxsize && key != 8){
    		alert('Достигнат максимален брой символи.');
    		elem.value = elem.value.slice(0, -1);
    		return false; 
    	} else {
    		return true;
    	}
    	return false; 
    }
    