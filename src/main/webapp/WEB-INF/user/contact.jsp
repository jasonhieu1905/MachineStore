<head>
<style type="text/css">
	#map {
		margin-top : 5%;
		width: 100%;
		height: 400px;
	}
</style>
</head>
<div>

		<div id="map"></div>
		<script>
			function initMap() {
				var mapDiv = document.getElementById('map');
				var map = new google.maps.Map(mapDiv, {
					center : {
						lat : 44.540,
						lng : -78.546
					},
					zoom : 8
				});
				var marker = new google.maps.Marker({
				    position: {
						lat : 44.540,
						lng : -78.546
					},
				    map: map,
				    title: 'Hello World!'
				  });
			}
		</script>
		<script src="https://maps.googleapis.com/maps/api/js?callback=initMap"
			async defer></script>
</div>