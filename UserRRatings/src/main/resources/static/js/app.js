$(document).ready(function(){
	$('.updateRating').click(function(e){
		e.preventDefault();
		var rating = $(this).attr('data-rating');
		var product = $(this).attr('data-product');
		
		$.ajax({
			url : "/v0/detail/product/"+product+"/rating/"+rating,
			type : "POST",
			contentType : "application/json",
			success : function(response) {
				
				$('span[data-product="'+product+'"]').removeClass('glyphicon-star-empty');
				$('span[data-product="'+product+'"]').removeClass('glyphicon-star');
				
				for(var jj = parseInt(rating) + 1; jj<=5; jj++){
					$('span[data-product="'+product+'"][data-rating="'+jj+'"]').addClass('glyphicon-star-empty');
				}
				
				for(var ii = 1; ii<=parseInt(rating); ii++){
					$('span[data-product="'+product+'"][data-rating="'+ii+'"]').addClass('glyphicon-star');
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(JSON.stringify(errorThrown));
			}
		});
	});
});