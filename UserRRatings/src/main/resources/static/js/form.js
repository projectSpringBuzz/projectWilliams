$(document).ready(function(){
	
	$("#btnSubmit").click(function(){
		
		if($("input[name='product1']").val() == '' && $("input[name='product1']").val() == '' && 
				$("input[name='product1']").val() == ''){
			alert('Missing products');
		}else {
		
			var object = {
				idphonenumber : null,
				phonenumber : $("#phoneNumber").val(),
				orderID : null,
				details : [
					{
						orderID : null,
						productName : $("input[name='product1']").val(),
						rating : $("input[name='stars1']").val()
					},
					{
						orderID : null,
						productName : $("input[name='product2']").val(),
						rating : $("input[name='stars2']").val()
					},
					{
						orderID : null,
						productName : $("input[name='product3']").val(),
						rating : $("input[name='stars3']").val()
					}
				],
				notes : [
					{
						orderID : null,
						notes : $("#notas").val()
					}
				]
			};
			
			$.ajax({
				url : "/v0/detail",
				type : "POST",
				data: JSON.stringify(object),
				contentType : "application/json",
				success : function(response) {
					alert("OK... data updated");
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(JSON.stringify(errorThrown));
				}
			});
			
		}
	});
	
});