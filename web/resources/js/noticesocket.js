$(function() {

	var writeToChatboard = function(text) {
		$("#chatarea").val(text + "\n" + $("#chatarea").val());
	}

	var url = "ws://localhost:8080/RTNoticeBoard/notice";
	var socket = new WebSocket(url);

	socket.onopen = function() {
		writeToChatboard("Connected to chat server");
	}
	socket.onclose = function() {
		writeToChatboard("Disconnected from chat server");
	}
//        $('#post-btn').attr('onclick',function() {
//            alert("sending..")
//            var msg = {"title" : $('#notice-title').val() , "category":$('#notice-category').val() ,"content": $('#notice-content').val() , "time" : new Date().val()}
//		socket.send(msg);
//	})
	$('#post-btn').attr('onclick',alert("sending"),socket.send('msg'))
            

        socket.onmessage = function (evt) {
                    alert('message received')
                    var msg = JSON.parse(evt.data);
                    var myData = JSON.parse(msg);
                    $("#all-notices-list").val("<li>ONE</li>"+$('#all-notices-list.val').val());
                }
})
