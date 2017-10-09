<script>
$(document).ready(function() {
	$("input[name='ISBN']").keydown(function(e){
		var ISBN = $(this).val();
		$.get('BorAction', {
			Action : "getSerial",
			ISBN : ISBN
		}, function(result) {
			$("input[name='SerialPlaceholder']").val(result);
			$("input[name='Serial']").val(result);
			if(result>0){
				$("input[name='BtnLend']").prop("disabled",false);
			}else{
				$("input[name='BtnLend']").prop("disabled",true);
			}
		});
		
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
	});
	
	$("input[name='MemID']").keydown(function(e){
		var MemID = $(this).val();
		$.get('BorAction', {
			Action : "getMemFName",
			MemID : MemID
		}, function(result) {
			$("input[name='MemFNamePlaceholder']").val(result);
		});
		
		$.get('BorAction', {
			Action : "getMemLName",
			MemID : MemID
		}, function(result) {
			$("input[name='MemLNamePlaceholder']").val(result);
		});
		
		$.get('BorAction', {
			Action : "getMemNotice",
			MemID : MemID
		}, function(result) {
			$("textarea[name='MemNoticePlaceholder']").val(result);
		});
	});
});
</script>

<div class="content">
	<div class="contentTitle">Borrow</div>
	<div class="vmargin" style="height:10px;"></div>
	<form method="post" action="BorAction" class="formSingle">
		<input type="text" name="ISBN" placeholder="ISBN" required style="width:calc(100% - 64px)">
		<input type="text" name="SerialPlaceholder" placeholder="#" disabled style="width:34px">
		<input type="hidden" name="Serial" required>
		<div class="vmargin" style="height:10px;"></div>
		<input type="text" name="BookTitlePlaceholder" placeholder="Book Title" disabled>
		<div class="vmargin" style="height:10px;"></div>
		<textarea name="BookDescriptionPlaceholder" placeholder="Book Description" disabled></textarea>
		<div class="vmargin" style="height:10px;"></div>
		<input type="text" name="MemID" placeholder="Member ID" required>
		<div class="vmargin" style="height:10px;"></div>
		<input type="text" name="MemFNamePlaceholder" placeholder="Member First Name" disabled style="width:calc(50% - 15px)">
		<input type="text" name="MemLNamePlaceholder" placeholder="Member Last Name" disabled style="width:calc(50% - 15px)">
		<div class="vmargin" style="height:10px;"></div>
		<textarea name="MemNoticePlaceholder" placeholder="Member Notice" disabled></textarea>
		<div class="vmargin" style="height:10px;"></div>
		<input type="number" name="Duration" placeholder="Duration" required min=1 max=7>
		<div class="vmargin" style="height:10px;"></div>
		<input type="submit" name="BtnLend" value="Lend" disabled>
	</form>
</div>