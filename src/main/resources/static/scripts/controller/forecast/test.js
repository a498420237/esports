function a(type, page, current) {
	// 如果想要去掉页码数字上面的预览功能，则在此操作。例如：可以直接return。
	switch (type) {
	case "first":
		return "Go to first page";
	case "prev":
		return "Go to previous page";
	case "next":
		return "Go to next page";
	case "last":
		return "Go to last page";
	case "page":
		return (page === current) ? "Current page is " + page : "Go to page "
				+ page;
	}
}