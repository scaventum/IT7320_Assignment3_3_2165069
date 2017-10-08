<div class="content">
	<div class="contentTitle">Return</div>
	<div class="vmargin" style="height:10px;"></div>
	<form method="post" action="borrowAction" class="formSingle">
		<input type="text" name="ISBN" placeholder="ISBN" required style="width:calc(100% - 64px)">
		<input type="text" name="Serial" placeholder="#" required style="width:34px">
		<div class="vmargin" style="height:10px;"></div>
		<input type="text" name="BookTitlePlaceholder" placeholder="Book Title" disabled>
		<div class="vmargin" style="height:10px;"></div>
		<textarea name="BookDescriptionPlaceholder" placeholder="Book Description" disabled></textarea>
		<div class="vmargin" style="height:10px;"></div>
		<input type="text" name="MemID" placeholder="Member ID" disabled>
		<div class="vmargin" style="height:10px;"></div>
		<input type="text" name="MemFName" placeholder="Member First Name" disabled style="width:calc(50% - 15px)">
		<input type="text" name="MemLName" placeholder="Member Last Name" disabled style="width:calc(50% - 15px)">
		<div class="vmargin" style="height:10px;"></div>
		<textarea name="RetNoticePlaceholder" placeholder="Return Notice" disabled></textarea>
		<div class="vmargin" style="height:10px;"></div>
		<input type="submit" name="BtnReturn" value="Return" disabled>
	</form>
</div>