// khi nào file html đc load thì thực hiện j đó
$(document).ready(function() {
	//Đăng ký sự kiện click: $(" tên_thẻ || tên_class || id").click
	// class => .
	// id => #
	// Đăng ký sự kiện click cho toàn bộ class có tên là btn-xoa
	$(".btn-xoa").click(function() {
		// lấy giá trị của thuộc tính (attr) bên thẻ có class là .btn-xoa
		// $(this): đại diện cho function đang thực thi
		var id = $(this).attr('id-user')
		var This = $(this)
		$.ajax({
			method: "GET",
			url: `http://localhost:8080/crm_project_02/api/user/delete?id=${id}`, //String literals
			data: { name: "John", location: "Boston" }// tham số data chỉ giành cho phương thức POST
		})
			.done(function(result) {
				// result đại diện cho kết quả từ link url trả về
				//Lấy giá trị kiểu Object trong js thì tênbiên.key
				if(result.data == true){
					// .parent => đi ra thẻ cha
					// .closest => đi ra thằng cha được chỉ định
					// .remove => xóa thẻ
					//VD-parent: This.parent().parent().remove()
					This.closest('tr').remove()
				}
				console.log(result)
			});

	})
})