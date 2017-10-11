<script>

function fillData(ISBN,Serial){
	var MemID = "";
	$.get('BorAction', {
		Action : "getBookTitle",
		ISBN : ISBN
	}, function(result) {
		$("input[name='BookTitlePlaceholder']").val(result);
	});
	
	$.get('BorAction', {
		Action : "getBookDescription",
		ISBN : ISBN
	}, function(result) {
		$("textarea[name='BookDescriptionPlaceholder']").val(result);
	});	

	$.get('BorAction', {
		Action : "getMemID",
		ISBN : ISBN,
		Serial : Serial
	}, function(result) {
		$("input[name='MemIDPlaceholder']").val(result);
		$("input[name='MemID']").val(result);
		$.get('BorAction', {
			Action : "getMemFName",
			MemID : result
		}, function(result2) {
			$("input[name='MemFNamePlaceholder']").val(result2);
		});
		
		$.get('BorAction', {
			Action : "getMemLName",
			MemID : result
		}, function(result2) {
			$("input[name='MemLNamePlaceholder']").val(result2);
		});
	});	
	
	$.get('BorAction', {
		Action : "getRetNotice",
		ISBN : ISBN,
		Serial : Serial
	}, function(result) {
		$("textarea[name='RetNoticePlaceholder']").val(result);
		if(result=="Book is not in borrowed list. "){
			$("input[name='BtnReturn']").prop("disabled",true);
		}else{
			$("input[name='BtnReturn']").prop("disabled",false);
		}
	});
	
	
}
$(document).ready(function() {
	
	$("input[name='ISBN']").keydown(function(e){
		var ISBN = $(this).val();
		var Serial = $("input[name='Serial']").val();
		fillData(ISBN,Serial);
	});
	
	$("input[name='Serial']").keydown(function(e){
		var ISBN = $("input[name='ISBN']").val();
		var Serial = $(this).val();
		fillData(ISBN,Serial);
	});
	
});
</script>

<div class="content">
	<div class="contentTitle">Return</div>
	<div class="vmargin" style="height:10px;"></div>
	<form method="post" action="BorAction" class="formSingle">
		<input type="text" name="ISBN" placeholder="ISBN" required style="width:calc(100% - 64px)">
		<input type="text" name="Serial" placeholder="#" required style="width:34px">
		<div class="vmargin" style="height:10px;"></div>
		<input type="text" name="BookTitlePlaceholder" placeholder="Book Title" disabled>
		<div class="vmargin" style="height:10px;"></div>
		<textarea name="BookDescriptionPlaceholder" placeholder="Book Description" disabled></textarea>
		<div class="vmargin" style="height:10px;"></div>
		<input type="text" name="MemIDPlaceholder" placeholder="Member ID" disabled>
		<input type="hidden" name="MemID">
		<div class="vmargin" style="height:10px;"></div>
		<input type="text" name="MemFNamePlaceholder" placeholder="Member First Name" disabled style="width:calc(50% - 15px)">
		<input type="text" name="MemLNamePlaceholder" placeholder="Member Last Name" disabled style="width:calc(50% - 15px)">
		<div class="vmargin" style="height:10px;"></div>
		<textarea name="RetNoticePlaceholder" placeholder="Return Notice" disabled></textarea>
		<div class="vmargin" style="height:10px;"></div>
		<input type="submit" name="BtnReturn" value="Return" disabled>
	</form>
</div>