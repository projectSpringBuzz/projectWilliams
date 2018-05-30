$(document).ready(function(){
	
	var evtSource = new EventSource("/v0/detail/polling/phoneNumber");
	evtSource.onmessage = function(e) {
		var data = $.parseJSON(e.data);
		console.log("data status: "+data.status);
		if(data.status == "OK"){
		
			var html = "";
			$(".principal").html('');
			$(data.list).each(function(ind, elem){
					html += '<p><button class="btn btn-primary" type="button" ' + 
						'data-toggle="collapse" data-target=".multi-collapse-'+elem.orderID+'" aria-expanded="false" '+
						'aria-controls="multiCollapseExample1-'+elem.orderID+' multiCollapseExample2-'+elem.orderID+'"> '+
						'View Order '+elem.orderID+'</button></p>';
					
					html += '<div class="panel panel-primary collapse multi-collapse-'+elem.orderID+'" '+ 
							'id="multiCollapseExample1-'+elem.orderID+'"> '+
							'<div class="panel-heading"> '+
							'<h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span> Products </h3> '+
							'</div><div class="panel-body"> ';
							
					$(elem.details).each(function(indx, detail){
						html += '<div class="radio"><label> '+detail.productName;
						
						for(i=1; i<=detail.rating; i++){
							html += '<a href="#" data-rating="'+i+'" '+
									'data-product="'+detail.productName+'" data-order="'+detail.orderID+'" '+
									'class="updateRating"> <span data-product="'+detail.productName+'" ' +
									'data-order="'+detail.orderID+'" data-rating="'+i+'" '+
									'class="glyphicon glyphicon-star"></span></a>';
						}
						
						if(detail.rating < 5){
							for(j = detail.rating + 1; j <=5; j++){
								html += '<a href="#" data-rating="'+j+'" ' +
										'data-product="'+detail.productName+'" data-order="'+detail.orderID+'" ' +
										'class="updateRating"> <span data-product="'+detail.productName+'" ' +
										'data-order="'+detail.orderID+'" data-rating="'+j+'" ' +
										'class="glyphicon glyphicon-star-empty"></span> </a>';
							}
						}
						html += '</label></div>';
					});
					
					html += '<div class="panel panel-primary collapse multi-collapse-'+elem.orderID+'" '+
					'id="multiCollapseExample2-'+elem.orderID+'"> '
					'<div class="panel-heading"><h3 class="panel-title"><span class="glyphicon glyphicon-list-alt"></span> Notes</h3></div> '+
					'<div class="panel-body"> ';
					
					$(elem.notes).each(function(indx, note){
						html += '<div class="form-group"> '+
								'<textarea class="form-control" rows="3" disabled>'+note.notes+'</textarea>' +
							'</div>';
					});
					
					html += '</div></div>';
					$(".principal").append(html);
					html = "";
			});
		}
	}
	
	$('.updateRating').click(function(e){
		e.preventDefault();
		var rating = $(this).attr('data-rating');
		var product = $(this).attr('data-product');
		var order = $(this).attr('data-order');
		
		$.ajax({
			url : "/v0/detail/"+order+"/rating/"+rating+"?productName="+product,
			type : "POST",
			contentType : "application/json",
			success : function(response) {
				
				$('span[data-product="'+product+'"][data-order="'+order+'"]').removeClass('glyphicon-star-empty');
				$('span[data-product="'+product+'"][data-order="'+order+'"]').removeClass('glyphicon-star');
				
				for(var jj = parseInt(rating) + 1; jj<=5; jj++){
					$('span[data-order="'+order+'"][data-product="'+product+'"][data-rating="'+jj+'"]').addClass('glyphicon-star-empty');
				}
				
				for(var ii = 1; ii<=parseInt(rating); ii++){
					$('span[data-order="'+order+'"][data-product="'+product+'"][data-rating="'+ii+'"]').addClass('glyphicon-star');
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(JSON.stringify(errorThrown));
			}
		});
	});
});