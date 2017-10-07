<div class="content">
	<div class="contentTitle">Borrow</div>
	<div class="vmargin" style="height:10px;"></div>
	<form method="post" action="borAction" class="formSingle">
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
		<input type="text" name="MemFName" placeholder="Member First Name" disabled style="width:calc(50% - 15px)">
		<input type="text" name="MemLName" placeholder="Member Last Name" disabled style="width:calc(50% - 15px)">
		<div class="vmargin" style="height:10px;"></div>
		<textarea name="MemNoticePlaceholder" placeholder="Member Notice" disabled></textarea>
		<div class="vmargin" style="height:10px;"></div>
		<input type="number" name="Duration" placeholder="Duration" required min=1 max=7>
		<div class="vmargin" style="height:10px;"></div>
		<input type="submit" name="BtnLend" value="Lend" disabled>
	</form>
</div>