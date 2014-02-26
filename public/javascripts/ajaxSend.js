$(document).ready(function() {

	// on click event 
	$('#ajax').click(function() {

		// find the device ID
		var devID = $('.selectDevice').val();

		// find the tester ID
		var testIDStr = $('.selectTester').val();

		// find the selected devices text
		var devName = $('.selectDevice option:selected').text();
		console.log(devName);

		// user should choose at least one tester and a device
		if(devID === null || testIDStr === null) {
			window.alert("You must select from option!");
		} else { 

			// countries selected can be duplicate
			var newString = jQuery.unique(testIDStr);

			// just if you select all devices. Not really a good way to do it but..
			if( devID == "ALL" ) {
				devID = $.map($(".selectDevice>option"), function(e) {
					return e.value;
				});
				devID.join(',');
			}

			// show them a button below
			$('.showBugs').show();

			// loop through countries
			for (var i=0; i<newString.length; i++) {
				// send request to get the testers
				var getTesters = $.get('/getUsers/' + newString[i], function(data) {
					$('.twoCol').html(data);

					// we create a 'li', now loop through each
					$('li').each(function(index) {
						var see = $( this ).text();
						var tempIDArr = see.split(" ");

						var tempID = tempIDArr[0];

						// send the request to get bugs
						var getBugs = $.get('/getBugs/' + tempID + "/" + devID);
var i=0;
						getBugs.done(function(bugData) {
							console.log(bugData);

							$.each(bugData, function(j) {
								console.log("this one : " + bugData[j].length);
								$.each(this, function(v) {
//									console.log(this);
//									console.log(" : " + v.bugId + " : " + v.tester.testerId);
								});
								$('.newCol').append("<b>" + tempIDArr[2] + " " + tempIDArr[3] + "</b> " + bugData[j].length);
								$('.newCol').append(" for " + devID[i] + "<br />"); i++;
							});
//							$('.newCol').append("<b>" + tempIDArr[2] + " " + tempIDArr[3]);
//							$('.newCol').append("</b>" + bugData + " <br>" + devName + " <br><br>");
						});

					});
				});
			}
		}

	});

	$('.selectTester').change(function() {
		var testID = $('.selectTester').val();
		$('.showBugs').hide();
		var getTesters = $.get('/getUsers/' + testID);
		getTesters.done(function(data) {

			/*  if user selects multiple tester countries then append it, otherwise replace it  */
			if(testID.length > 1) {
				$('.newCol').append(data);
			} else {
				$('.newCol').html(data);
			}
		});

	});

	$('.selectDevice').change(function() {
		var devID = $('.selectDevice').val();

		//var getDevs = $.get('/getUsers/' + testID);

	});

	$('#showVals').click(function() {
		$('.hideMe').show();
		console.log(" triggered this ");
	});


});