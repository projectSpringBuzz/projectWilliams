$(document).ready(function(){
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