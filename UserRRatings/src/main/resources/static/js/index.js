$(document).ready(function(){
	$('.btnSearch').click(function(e){
		if($("#phoneNumber").val() == ''){
			alert('Phone Number not found');
		}else{
			window.location.href="details/"+$("#phoneNumber").val();
		}
	});
});