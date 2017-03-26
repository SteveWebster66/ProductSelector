function addToBasket(checkbox) {

	    var ul = document.getElementById("basket");

	    if (checkbox.checked) {
	    	var li = document.createElement("li");
		    li.innerHTML = checkbox.value;
	    	ul.appendChild(li);
  		} else {
  			var items = ul.getElementsByTagName("li");
  			for (var i = 0; i < items.length; ++i) {
  				 if (items[i].innerHTML == checkbox.value) {
   				  ul.removeChild(items[i]);
   			  }
    		}
	    }
  }