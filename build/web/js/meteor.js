/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$("#fuzzySearch").on("keypress", function (e) {
	var title = e.target.value;
	$.ajax({
		url: "FuzzySearch",
		data: {
			method: "ajax",
			title: title + String.fromCharCode(e.keyCode)
		},
		type: "GET",
		dataType: "JSON",
		success: function (result) {
			$("#searchResult").empty();
			for (var i = 0; i < result.length; i++) {
				$("#searchResult").append("<option value='" + result[i] + "'>");
			}
		}
	});
});

$("#fuzzySearch").on("keypress", function (e) {
	if (e.keyCode === 13) {
		var title = e.target.value;
		$.ajax({
			url: "FuzzySearchToSingleMovie",
			data: {
				method: "forward",
				title: title
			},
			type: "POST",
			success: function (result) {
				document.write(result);
			}
		});
	}
});

$('[name="movieName"]').on("mouseover", function (e) {
	var value = e.currentTarget.attributes[3].value;
	$.ajax({
		url: "MovieInfo",
		data: {
			id: value
		},
		type: "GET",
		dataType: "JSON",
		success: function (movieInfo) {
			$('[name="MovieInfoToolTip"]').empty();
			$('[name="MovieInfoToolTip"]').append("<img src='" + movieInfo.banner + "' style='width:80px;height:100px;'/><br/>" +
				"Stars: " + movieInfo.stars + "<br/>" +
				"Year: " + movieInfo.year + "<br/>" +
				"<form action="
				+ "/MovieWebApp/ShoppingCart"
				+ " method='POST'>"
				+ "<div style='padding-left: 35%;'>"
				+ "<button value=" + movieInfo.id
				+ " name='movieAddedToCart'>"
				+ "<i class='fa fa-shopping-cart' aria-hidden='true'></i>"
				+ "&nbsp;add"
				+ "</button>"
				+ "</div>"
				+ "</form>");
		}
	});
});