var orderId;
function paymentConfirmation() {
	   $("input[name^='confirmpayment'").each(function() {
		var paymentuserid = $("input[name^=paymentuserid]").val();
		var paymentsubid = $("input[name^=paymentsubid]").val();
		var paymentamount = $("input[name^=paymentamount]").val();
		if ($(this).prop('checked')) {
			var paymentstatusId = $(this).val();

			$.ajax({
				type : "POST",
				data : {
					"paymentstatusId" : paymentstatusId
				},
				url : window.location + "/paymentstatus",
				success : function(data, status) {
				},
				error : function(e) {

					console.log("ERROR: ", e);
				}
			});

		}
	});
	window.location.reload();
}

function individualStockList() {

	var languageId = $("select[name=languages]").val();
	var authorId = $("select[name=authors]").val();
	var publisherId = $("select[name=publishers]").val();
	var redingLevelId = $("select[name=readingLevels]").val();
	$.ajax({
		type : "GET",
		data : {
			"publisherId" : publisherId,
			"languageId" : languageId,
			"authorId" : authorId,
			"redingLevelId" : redingLevelId
		},
		url : window.location + "/individualstock",
		success : function(data, status) {
			var dataSet = data;
			if ($.fn.DataTable.isDataTable('#reportIndividualBookTable')) {
				$('#reportIndividualBookTable').DataTable().destroy();
			}
			$('#reportIndividualBookTable').DataTable({
				data : dataSet,
				columns : [ {
					"mData" : 0
				}, {
					"mData" : 5
				}, {
					"mData" : 1
				}, {
					"mData" : 2
				},
				{
					"mData" : 3
				},
				{
					"mData" : 8
				},
				{
					"mData" : 6
				},]
			});
		},
		error : function(e) {

			console.log("ERROR: ", e);
		}

	});
}

function userAreaBasdeReport() {
	var areaId = $("select[name=pincodes]").val();
	$.ajax({
		type : "GET",
		data : {
			"areaId" : areaId
		},
		url : window.location + "/reportareausers",
		success : function(data, status) {
			var dataSet = data;
			if ($.fn.DataTable.isDataTable('#userAreaBasedReportTable')) {
				$('#userAreaBasedReportTable').DataTable().destroy();
			}
			$('#userAreaBasedReportTable').DataTable(
					{
						data : dataSet,
						columns : [
								{
									"mData" : 0
								},
								{
									"mData" : 2
								},
								{
									"mData" : 3
								},
								{
									mRender : function(data, type, row) {
										var result = '' + row[4] + ',' + row[5]
												+ ',' + row[6] + ',' + row[7]
												+ ',' + row[8];
										return result;

									}
								}, ]
					});
		},
		error : function(e) {

			console.log("ERROR: ", e);
		}
	});
}

function planWiseUserReport() {
	var subscriptionId = $("select[name=subscriptions]").val();
	$.ajax({
		type : "GET",
		data : {
			"subscriptionId" : subscriptionId
		},
		url : window.location + "/planbaseuser",
		success : function(data, status) {
			var dataSet = data;
			if ($.fn.DataTable.isDataTable('#planBaseUserReportTable')) {
				$('#planBaseUserReportTable').DataTable().destroy();
			}
			$('#planBaseUserReportTable').DataTable(
					{
						data : dataSet,
						columns : [
								{
									"mData" : 0
								},
								{
									"mData" : 1
								},
								{
									"mData" : 2
								},
								{
									mRender : function(data, type, row) {
										var myDate = new Date(row[3]);
										var date = myDate.getDate() + "-"
												+ (myDate.getMonth() + 1) + "-"
												+ myDate.getFullYear();
										return date;

									}
								}, ]
					});
		},
		error : function(e) {

			console.log("ERROR: ", e);
		}

	});
}
function todayEmployeeWiseReport() {
	var staffId = $("select[name=staffs]").val();
	$.ajax({
		type : "GET",
		data : {
			"staffId" : staffId
		},
		url : window.location + "/staffdatereporttoday",
		success : function(data, status) {
			var dataSet = data;
			if ($.fn.DataTable.isDataTable('#reportStaffTodayTable')) {
				$('#reportStaffTodayTable').DataTable().destroy();
			}
			$('#reportStaffTodayTable').DataTable(
					{
						data : dataSet,
						columns : [
								{
									"mData" : 0
								},
								{
									"mData" : 1
								},
								{
									"mData" : 14
								},
								{
									mRender : function(data, type, row) {
										if (row[7] == null) {
											return "Pending Delivery";
										} else {
											var myDate = new Date(row[7]);
											var date = myDate.getDate() + "-"
													+ (myDate.getMonth() + 1)
													+ "-"
													+ myDate.getFullYear();
											return date;
										}
									}
								},
								{
									mRender : function(data, type, row) {
										if (row[10] == null) {
											return "Pickup Pending";
										} else {
											var myDate = new Date(row[10]);
											var date = myDate.getDate() + "-"
													+ (myDate.getMonth() + 1)
													+ "-"
													+ myDate.getFullYear();
											return date;
										}
									}
								},
								{
									"mData" : 3
								},
								{
									mRender : function(data, type, row) {
										var result = '' + row[4] + ',' + row[5]
												+ ',' + row[6];
										return result;

									}
								}, ]
					});
		},
		error : function(e) {

			console.log("ERROR: ", e);
		}
	});
}

function weekEmployeeWiseReport() {
	var staffId = $("select[name=staffs]").val();
	$.ajax({
		type : "GET",
		data : {
			"staffId" : staffId
		},
		url : window.location + "/staffweekreport",
		success : function(data, status) {
			var dataSet = data;
			if ($.fn.DataTable.isDataTable('#reportStaffTodayTable')) {
				$('#reportStaffTodayTable').DataTable().destroy();
			}
			$('#reportStaffTodayTable').DataTable(
					{
						data : dataSet,
						columns : [
								{
									"mData" : 0
								},
								{
									"mData" : 1
								},
								{
									"mData" : 14
								},
								{
									mRender : function(data, type, row) {
										if (row[7] == null) {
											return "Pending Delivery";
										} else {
											var myDate = new Date(row[7]);
											var date = myDate.getDate() + "-"
													+ (myDate.getMonth() + 1)
													+ "-"
													+ myDate.getFullYear();
											return date;
										}
									}
								},
								{
									mRender : function(data, type, row) {
										if (row[10] == null) {
											return "Pending Pickup";
										} else {
											var myDate = new Date(row[10]);
											var date = myDate.getDate() + "-"
													+ (myDate.getMonth() + 1)
													+ "-"
													+ myDate.getFullYear();
											return date;

										}
									}
								},
								{
									"mData" : 3
								},
								{
									mRender : function(data, type, row) {
										var result = '' + row[4] + ',' + row[5]
												+ ',' + row[6];
										return result;

									}
								}, ]
					});
		},
		error : function(e) {

			console.log("ERROR: ", e);
		}
	});
}
function searchDateWiseBooksDetails() {

	var fromDate = $("input[name=fromDate]").val();
	var tillDate = $("input[name=tillDate]").val();
	$.ajax({
		type : "GET",
		data : {
			"fromDate" : fromDate,
			"tillDate" : tillDate
		},
		url : window.location + "/datewisereport",
		success : function(data, status) {
			var dataSet = data;
			if ($.fn.DataTable.isDataTable('#searchbookTable')) {
				$('#searchbookTable').DataTable().destroy();
			}
			$('#searchbookTable').DataTable({
				data : dataSet,
				columns : [ 
					      {
					         "mData" : 0
				          },
				          {
			                  "mData" : 6
		                  }, 
		                  {
			                  "mData" : 1
		                  },
		                  {
			                  "mData" : 2
		                  },
		                  {
			                 "mData" : 3
		                  },
		                  {
			                 "mData" : 5
		                  },
		                  {
			                 "mData" : 9
		                  }, 
		]
			});
		},
		error : function(e) {

			console.log("ERROR: ", e);
		}
	});

}

function printFunction() {
	window.print();
}
function deliveryCheckAll() {
	$("input[name^='delivery_chkbox_']").each(function() {
		$(this).prop('checked', true);
	});
}

function deliveryDeCheckAll() {
	$("input[name^='delivery_chkbox_']").each(function() {
		$(this).prop('checked', false);
	});
}

function deliveryCheckAll() {
	$("input[name^='pickup_chkbox_']").each(function() {
		$(this).prop('checked', true);
	});
}

function deliveryDeCheckAll() {
	$("input[name^='pickup_chkbox_']").each(function() {
		$(this).prop('checked', false);
	});
}


function saveAllConfirmPickup() {
	$("input[name^='pickupconfirm']").each(function() {
		if ($(this).prop('checked')) {
			var borrowId = $(this).val();

			$.ajax({
				type : "POST",
				data : {
					"borrowID" : borrowId
				},
				url : window.location + "/confirmedbook",
				success : function(data, status) {
				},
				error : function(e) {

					console.log("ERROR: ", e);
				}
			});

		}
	});
	window.location.reload();
}

function saveAllPaymentCashConfirm() {
	$("input[name^='confirmcashpayment']").each(function() {
		if ($(this).prop('checked')) {
			var uscId = $(this).val();

			$.ajax({
				type : "POST",
				data : {
					"uscId" : uscId
				},
				url : window.location + "/confirmCashPayment",
				success : function(data, status) {
				},
				error : function(e) {

					console.log("ERROR: ", e);
				}
			});

		}
	});
	window.location.reload();
}


function Testimonial() {
	$("input[name^='confirmtestimonial']").each(function() {
		if ($(this).prop('checked')) {
			var testimonialId = $(this).val();
			$.ajax({
				type : "POST",
				data : {
					"testimonialId"  : testimonialId
				},
				url : window.location + "/confirmtestimonials",
				success : function(data, status) {
				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});

		}
	});
	window.location.reload();
}

function saveAllDelivery() {
	$("input[name^='delivery_chkbox_']").each(function() {
		if ($(this).prop('checked')) {
			var borrowId = $(this).val();
			$.ajax({
				type : "POST",
				data : {
					"borrowId" : borrowId
				},
				url :"/bookshelfAdmin/confirmdelivery",
				success : function(data, status) {
				},
				error : function(e) {

					console.log("ERROR: ", e);
				}
			});

		}
	});
	window.location.reload();
}

function saveAllUnDelivery() {
	$("input[name^='undelivery_chkbox_']").each(function() {
		if ($(this).prop('checked')) {
			var borrowId = $(this).val();
			var unDeliveryReasonVal = $("select[name=unDeliveryReason]").val();

			
			$.ajax({
				type : "POST",
				data : {
					"borrowId" : borrowId,
					"unDeliveryReason":unDeliveryReasonVal
				},
				url :"/bookshelfAdmin/undelivery",
				success : function(data, status) {
				},
				error : function(e) {

					console.log("ERROR: ", e);
				}
			});

		}
	});
	window.location.reload();
}





function saveAllPickup() {
$("input[name^='pickup_chkbox_']").each(function() {
	if ($(this).prop('checked')) {
		var borrowId = $(this).val();
		$.ajax({
			type : "POST",
			data : {
				"borrowId" : borrowId
			},
			url :"/bookshelfAdmin/confirmpickup",
			success : function(data, status) {
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});

	}
});
window.location.reload();
}


function saveAllUnPickup() {
	$("input[name^='unpickup_chkbox_']").each(function() {
		if ($(this).prop('checked')) {
			var borrowId = $(this).val();
			var unDeliveryReasonVal = $("select[name=unDeliveryReason]").val();

			
			$.ajax({
				type : "POST",
				data : {
					"borrowId" : borrowId,
					"unDeliveryReason":unDeliveryReasonVal
				},
				url :"/bookshelfAdmin/unpickup",
				success : function(data, status) {
				},
				error : function(e) {

					console.log("ERROR: ", e);
				}
			});

		}
	});
	window.location.reload();
}


// manage data tables
$(document)
		.ready(
				function() {
					var table = $('#areaTable')	
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/areas",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updatearea/'
																+ row.areaID
																+ '"  >'
																+ row.areaID
																+ '</a>'

													}

												},
												{
													"mData" : "area"
												},
												{
													"mData" : "pincode"
												},
												{
													mRender : function(data,
															type, row) {
														if (row.isActive == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deletearea/'
																	+ row.areaID
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deletearea/'
																	+ row.areaID
																	+ '/1"  >DeActive</a>'
														}
													}
												}

										]
									});

					var table = $('#authorTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/authors",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updateauthor/'
																+ row.authorID
																+ '"  >'
																+ row.authorID
																+ '</a>'

													}

												},
												{
													"mData" : "authorName"
												},

												{
													mRender : function(data,
															type, row) {
														if (row.isActive == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deleteauthor/'
																	+ row.authorID
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deleteauthor/'
																	+ row.authorID
																	+ '/1"  >DeActive</a>'
														}
													}
												}

										]
									});

					var table = $('#bookcategoryTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/bookcategories",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updatebookcategory/'
																+ row.categoryId
																+ '"  >'
																+ row.categoryId
																+ '</a>'

													}

												},
												{
													"mData" : "categoryName"
												},
												{
													"mData" : "description"
												},
												{
													mRender : function(data,
															type, row) {
														if (row.isActive == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deletebookcategory/'
																	+ row.categoryId
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deletebookcategory/'
																	+ row.categoryId
																	+ '/1"  >DeActive</a>'
														}
													}
												}

										]
									});

					var table = $('#languageTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/languages",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updatelanguage/'
																+ row.languageID
																+ '"  >'
																+ row.languageID
																+ '</a>'

													}

												},
												{
													"mData" : "language"
												},

												{
													mRender : function(data,
															type, row) {
														if (row.isActive == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deletelanguage/'
																	+ row.languageID
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deletelanguage/'
																	+ row.languageID
																	+ '/1"  >DeActive</a>'
														}
													}
												}

										]
									});

					var table = $('#publisherTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/publishers",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updatepublisher/'
																+ row.publisherId
																+ '"  >'
																+ row.publisherId
																+ '</a>'

													}

												},
												{
													"mData" : "publisherName"
												},

												{
													mRender : function(data,
															type, row) {
														if (row.isActive == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deletepublisher/'
																	+ row.publisherId
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deletepublisher/'
																	+ row.publisherId
																	+ '/1"  >DeActive</a>'
														}
													}
												}

										]
									});

					var table = $('#readinglevelTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/readinglevels",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updatereadinglevel/'
																+ row.readingLevelId
																+ '"  >'
																+ row.readingLevelId
																+ '</a>'

													}

												},
												{
													"mData" : "readingLevel"
												},
												{
													"mData" : "description"
												},
												{
													mRender : function(data,
															type, row) {
														if (row.isActive == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deletereadinglevel/'
																	+ row.readingLevelId
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deletereadinglevel/'
																	+ row.readingLevelId
																	+ '/1"  >DeActive</a>'
														}
													}
												}

										]
									});

					var table = $('#staffTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/staffs",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updatestaff/'
																+ row[0]
																+ '"  >'
																+ row[0]
																+ '</a>'

													}

												},
												{
													"mData" : 1
												},
												{
													"mData" : 2
												},
												{
													"mData" : 3
												},
												{
													"mData" : 4
												},

												{
													mRender : function(data,
															type, row) {
														if (row[5] == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deletestaff/'
																	+ row[0]
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deletestaff/'
																	+ row[0]
																	+ '/1"  >DeActive</a>'
														}
													}
												}

										]
									});

					var table = $('#bookTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/books",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updatebook/'
																+ row[0]
																+ '"  >'
																+ row[0]
																+ '</a>'

													}

												},
												{
													"mData" : 1
												},
												{
													"mData" : 2
												},
												{
													"mData" : 3
												},
												{
													"mData" : 4
												},
												{
													"mData" : 5
												},
												{
													"mData" : 6
												},
												{
													"mData" : 7
												},
												{
													"mData" : 8
												},
												{
													"mData" : 9
												},
												{
													"mData" : 10
												},
												{
													mRender : function(data,
															type, row) {
														if (row[11] == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deletebook/'
																	+ row[0]
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deletebook/'
																	+ row[0]
																	+ '/1"  >DeActive</a>'
														}
													}
												}, {
													"mData" : 13
												}

										]
									});

					var table = $('#deliveryslotTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/deliveryslots",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updatedeliveryslot/'
																+ row[0]
																+ '"  >'
																+ row[0]
																+ '</a>'

													}

												},
												{
													"mData" : 1
												},
												{
													"mData" : 2
												},
												{
													"mData" : 3
												},
												{
													mRender : function(data,
															type, row) {
														if (row[4] >= 12
																&& row[4] <= 24) {
															if (row[4] > 12) {
																return (row[4] - 12)
																		+ ' PM '
															} else {
																return (row[4])
																		+ ' PM '
															}

														} else {
															return row[4]
																	+ ' AM '
														}

													}

												},
												{
													mRender : function(data,
															type, row) {
														if (row[5] >= 12
																&& row[5] <= 24) {
															if (row[5] > 12) {
																return (row[5] - 12)
																		+ ' PM '
															} else {
																return (row[5])
																		+ ' PM '
															}
														} else {
															return row[5]
																	+ ' AM '
														}

													}

												},
												{
													"mData" : 6
												},
												{
													"mData" : 7
												},

												{
													mRender : function(data,
															type, row) {
														if (row[8] == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deletedeliveryslot/'
																	+ row[0]
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deletedeliveryslot/'
																	+ row[0]
																	+ '/1"  >DeActive</a>'
														}
													}
												}

										]
									});

					var table = $('#mydeliveryslotTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/mydeliveryslots",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updatedeliveryslot/'
																+ row[0]
																+ '"  >'
																+ row[0]
																+ '</a>'

													}

												},
												{
													"mData" : 1
												},
												{
													"mData" : 2
												},
												{
													"mData" : 3
												},
												{
													mRender : function(data,
															type, row) {
														if (row[4] >= 12
																&& row[4] <= 24) {
															if (row[4] > 12) {
																return (row[4] - 12)
																		+ ' PM '
															} else {
																return (row[4])
																		+ ' PM '
															}
														} else {
															return row[4]
																	+ ' AM '
														}

													}

												},
												{
													mRender : function(data,
															type, row) {
														if (row[5] >= 12
																&& row[5] <= 24) {
															if (row[5] > 12) {
																return (row[5] - 12)
																		+ ' PM '
															} else {
																return (row[5])
																		+ ' PM '
															}
														} else {
															return row[5]
																	+ ' AM '
														}

													}

												},
												{
													"mData" : 6
												},
												{
													"mData" : 7
												},

												{
													mRender : function(data,
															type, row) {
														if (row[8] == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deletedeliveryslot/'
																	+ row[0]
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deletedeliveryslot/'
																	+ row[0]
																	+ '/1"  >DeActive</a>'
														}
													}
												}

										]
									});
					var table = $('#subscriptionruleTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/subscriptionrules",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updatesubscriptionrule/'
																+ row.ruleId
																+ '"  >'
																+ row.ruleId
																+ '</a>'

													}

												},
												{
													"mData" : "ruleName"
												},
												{
													"mData" : "noofMonths"
												},
												{
													"mData" : "maxNumberofBooks"
												},
												{
													"mData" : "maxNumberofDelivery"
												},
												{
													"mData" : "rule"
												},
												{
													mRender : function(data,
															type, row) {
														if (row.isActive == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deletesubscriptionrule/'
																	+ row.ruleId
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deletesubscriptionrule/'
																	+ row.ruleId
																	+ '/1"  >DeActive</a>'
														}
													}
												}

										]
									});

					var table = $('#subscriptionTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/subscriptions",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													mRender : function(data,
															type, row) {

														return '<a class="table-edit" href="/bookshelfAdmin/updatesubscription/'
																+ row.subcId
																+ '"  >'
																+ row.subcId
																+ '</a>'

													}

												},
												{
													"mData" : "subscriptionName"
												},
												{
													"mData" : "subscriptionDescription"
												},
												{
													"mData" : "subscriptionSummary"
												},
												{
													"mData" : "noofMonths"
												},
												{
													"mData" : "maxNumberofBooks"
												},
												{
													"mData" : "maxNumberofDelivery"
												},
												{
													mRender : function(data,
															type, row) {
														if (row.isActive == 1) {
															return '<a class="table-edit" href="/bookshelfAdmin/deletesubscription/'
																	+ row.subcId
																	+ '/0"  >Active</a>'
														} else {
															return '<a class="table-edit" href="/bookshelfAdmin/deletesubscription/'
																	+ row.subcId
																	+ '/1"  >DeActive</a>'
														}
													}
												}

										]
									});
					// upcoming deliveries

					oTable = $('#myUpcomingDeliveriesTable')
							.dataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/myupcomingdeliverieslist",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
											{
												mRender : function(data,
														type, row) {

													return '<a class="table-edit" href="/bookshelfAdmin/deliveryorderdetailsList/'
															+ row[0]
															+ '"  >'
															+ row[0]
															+ '</a>'

												}

											},
												{
													"mData" : 16
												},
												{
													"mData" : 1
												},
												{
													mRender : function(data,
															type, row) {
														
														var myDate = new Date(row[18]);
														var date = myDate.getDate() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getFullYear();
														
														var result = ''
																+ date + ' Between '
																+ row[19] + ' to '
																+ row[20];
																
														return result;

													}
												},
												/*{
													mRender : function(data,
															type, row) {
														var result = ''
																+ row[2] + ','
																+ row[17];
														return result;

													}
												},*/
												{
													"mData" : 3
												},
												{
													mRender : function(data,
															type, row) {
														var result = ''
																+ row[4] + ','
																+ row[5] + ','
																+ row[6] + ','
																+ row[7] + ','
																+ row[8];
														return result;

													}
												},

												{
													mRender : function(data,
															type, row) {
														if (row[23] != null) {
															return "delivery";
														}
													}
												},

										]

									});
					
					oTable = $('#adminUpcomingDeliveriesTable')
					.dataTable(
							{
								"sAjaxSource" : "/bookshelfAdmin/adminupcomingdeliverieslist",
								"sAjaxDataProp" : "",
								"pagingType" : "full_numbers",
								"order" : [ [ 0, "asc" ] ],
								"aoColumns" : [
									{
										mRender : function(data,
												type, row) {

											return '<a class="table-edit" href="/bookshelfAdmin/admindeliveryorderdetailsList/'
													+ row[0]
													+ '"  >'
													+ row[0]
													+ '</a>'

										}

									},
										{
											"mData" : 24
										},
										{
											"mData" : 16
										},
										{
											"mData" : 1
										},
										{
											mRender : function(data,
													type, row) {
												
												var myDate = new Date(row[18]);
												var date = myDate.getDate() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getFullYear();
												var result = ''
														+ date + ' Between '
														+ row[19] + ' to '
														+ row[20];
														
												return result;

											}
											
										},
										/*{
											mRender : function(data,
													type, row) {
												var result = ''
														+ row[2] + ','
														+ row[17];
												return result;

											}
										},*/
										{
											"mData" : 3
										},
										{
											mRender : function(data,
													type, row) {
												var result = ''
														+ row[4] + ','
														+ row[5] + ','
														+ row[6] + ','
														+ row[7] + ','
														+ row[8];
												return result;

											}
										}

										/*{
											mRender : function(data,
													type, row) {
												if (row[23] != null) {
													return "delivery";
												}
											}
										},*/

								]

							});
					// complete deliveries
					
					oTable = $('#MyCompletedDeliveriesListTable')
					.dataTable(
							{
								"sAjaxSource" : "/bookshelfAdmin/mycompletedeliverieslist",
								"sAjaxDataProp" : "",
								"pagingType" : "full_numbers",
								"order" : [ [ 0, "asc" ] ],
								"aoColumns" : [
									   {
										    "mData" : 0
									    },
										{
											"mData" : 16
										},
										{
											"mData" : 1
										},
										{
											mRender : function(data,
													type, row) {
												var result = ''
														+ row[2] + ','
														+ row[17];
												return result;

											}
										},
										{
											"mData" : 3
										},
										{
											mRender : function(data,
													type, row) {
												var result = ''
														+ row[4] + ','
														+ row[5] + ','
														+ row[6] + ','
														+ row[7] + ','
														+ row[8];
												return result;

											}
										},

										{
											mRender : function(data,
													type, row) {
												if (row[14] != null) {
													return "delivery completed";
												} 
											}
										},

								]

							});
					
					
					// upcoming Pickups

					oTable = $('#MyUpcomingPickupsTable')
							.dataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/myupcomingpickupslist",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
											{
												mRender : function(data,
														type, row) {

													return '<a class="table-edit" href="/bookshelfAdmin/pickuporderdetailsList/'
															+ row[0]
															+ '"  >'
															+ row[0]
															+ '</a>'

												}

											},
												{
													"mData" : 16
												},
												{
													"mData" : 1
												},
												{ //habeep

													mRender : function(data,
															type, row) {
														
														var myDate = new Date(row[18]);
														var date = myDate.getDate() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getFullYear();
														
														var result = ''
																+ date + ' Between '
																+ row[19] + ' to '
																+ row[20];
																
														return result;


													}
												},
												{
													"mData" : 3
												},
												{
													mRender : function(data,
															type, row) {
														var result = ''
																+ row[4] + ','
																+ row[5] + ','
																+ row[6] + ','
																+ row[7] + ','
																+ row[8];
														return result;

													}
												},

												{
													mRender : function(data,
															type, row) {
														if (row[22] != null) {
															return "pickup";
														}
													}
												},

										]

									});
					
					// complete pickups
					
					oTable = $('#MyCompletedPickupsTable')
					.dataTable(
							{
								"sAjaxSource" : "/bookshelfAdmin/mycompletepickupslist",
								"sAjaxDataProp" : "",
								"pagingType" : "full_numbers",
								"order" : [ [ 0, "asc" ] ],
								"aoColumns" : [
									   {
										    "mData" : 0
									    },
										{
											"mData" : 16
										},
										{
											"mData" : 1
										},
										{
											mRender : function(data,
													type, row) {
												var result = ''
														+ row[2] + ','
														+ row[17];
												return result;

											}
										},
										{
											"mData" : 3
										},
										{
											mRender : function(data,
													type, row) {
												var result = ''
														+ row[4] + ','
														+ row[5] + ','
														+ row[6] + ','
														+ row[7] + ','
														+ row[8];
												return result;

											}
										},

										{
											mRender : function(data,
													type, row) {
												if (row[15] != null) {
													return "pickup complete";
												}
											}
										},

								]

							});
							
					
					
					
					// delivery orderid details
					
					var orderIdVal = $("#orderId").val();
					var urlIs = "/bookshelfAdmin/deliveryorderdetailsLists/"+orderIdVal;
					oTable = $('#deliveryordersdetailsTable')
					.dataTable(
							{
								"sAjaxSource" : urlIs,
								"sAjaxDataProp" : "",
								"pagingType" : "full_numbers",
								"order" : [ [ 0, "asc" ] ],
								"aoColumns" : [
									    {
										"mData" : 0
									    },
										{
											"mData" : 2
										},
										{
											"mData" : 17
										},
										{
											"mData" : 24
										},
										{
											mRender : function(data,
													type, row) {
												if (row[11] == 0) {
													return "delivery";
												} else {
													return "pickup";
												}
											}
										},
										{
											mRender : function(data,
													type, row) {

												if (row[11] == 0) {
													if (row[14] == null) {
														return '<input type="checkbox" id="delivery_chkbox_'
																+ row[13]
																+ '" name="delivery_chkbox_'
																+ row[13]
																+ '" value="'
																+ row[13]
																+ '" >Confirm Delivery</input>';
													} else {
														return "deliverycompleted";
													}
												} 
											}

										},
										{
											mRender : function(data,
													type, row) {

												if (row[11] == 0) {
													if (row[14] == null) {
														return '<input type="checkbox" id="undelivery_chkbox_'
																+ row[13]
																+ '" name="undelivery_chkbox_'
																+ row[13]
																+ '" value="'
																+ row[13]
																+ '" >Un Delivery</input>';
													} else {
														return "deliverycompleted";
													}
												} 
											}

										},

								]

							});
					
					//for Admin
					
					var orderIdVal1 = $("#orderId").val();
					var urlIs1 = "/bookshelfAdmin/admindeliveryorderdetailsLists/"+orderIdVal1;
					oTable = $('#admindeliveryordersdetailsTable')
					.dataTable(
							{
								"sAjaxSource" : urlIs1,
								"sAjaxDataProp" : "",
								"pagingType" : "full_numbers",
								"order" : [ [ 0, "asc" ] ],
								"aoColumns" : [
									    {
										"mData" : 0
									    },
										{
											"mData" : 2
										},
										{
											"mData" : 17
										},
										{
											"mData" : 24
										},
										{
											mRender : function(data,
													type, row) {
												if (row[11] == 0) {
													return "delivery";
												} else {
													return "pickup";
												}
											}
										},
										{
											mRender : function(data,
													type, row) {

												if (row[11] == 0) {
													if (row[14] == null) {
														return '<input type="checkbox" id="delivery_chkbox_'
																+ row[13]
																+ '" name="delivery_chkbox_'
																+ row[13]
																+ '" value="'
																+ row[13]
																+ '" >Confirm Delivery</input>';
													} else {
														return "deliverycompleted";
													}
												} 
											}

										},

								]

							});
					
					// pickup orderid details

					var orderIdVal = $("#orderId").val();
					var urlIs = "/bookshelfAdmin/pickuporderdetailsLists/"+orderIdVal;
					oTable = $('#pickupordersdetailsTable')
					.dataTable(
							{
								"sAjaxSource" : urlIs,
								"sAjaxDataProp" : "",
								"pagingType" : "full_numbers",
								"order" : [ [ 0, "asc" ] ],
								"aoColumns" : [
									    {
										"mData" : 0
									    },
										{
											"mData" : 16
										},
										{
											"mData" : 1
										},
										{
											mRender : function(data,
													type, row) {
												var result = ''
														+ row[2] + ','
														+ row[17];
												return result;

											}
										},
										{
											"mData" : 3
										},
										{
											mRender : function(data,
													type, row) {
												var result = ''
														+ row[4] + ','
														+ row[5] + ','
														+ row[6] + ','
														+ row[7] + ','
														+ row[8];
												return result;

											}
										},

										{
											mRender : function(data,
													type, row) {
												if (row[11] == 0) {
													return "delivery";
												} else {
													return "pickup";
												}
											}
										},
										{
											mRender : function(data,
													type, row) {
												 if (row[11] == 1) {
													if (row[15] == null) {
														return '<input type="checkbox" id="pickup_chkbox_'
																+ row[13]
																+ '" name="pickup_chkbox_'
																+ row[13]
																+ '" value="'
																+ row[13]
																+ '" >Confirm Pickup</input>';
													} else {
														return "pickupcompleted";
													}
												}

											}

										},
										{
											mRender : function(data,
													type, row) {

												if (row[11] == 1) {
													if (row[15] == null) {
														return '<input type="checkbox" id="unpickup_chkbox_'
																+ row[13]
																+ '" name="unpickup_chkbox_'
																+ row[13]
																+ '" value="'
																+ row[13]
																+ '" >Cancel Pickup</input>';
													} else {
														return "pickupcompleted";
													}
												} 
											}

										},

								]

							});
					
					var t = $('#stockTable').DataTable({
						"sAjaxSource" : "/bookshelfAdmin/stockbooks",
						"sAjaxDataProp" : "",
						"pagingType" : "full_numbers",
						"order" : [ [ 1, "asc" ] ],
						"aoColumns" : [ 
// {
// mRender : function(data,
// type, row) {
// if (row[0] != null) {
//
// return '<input type="hidden" id="bookidprint" name="bookidprint" value="'
// + row[0]
// + '">'
// + row[0]
// + '</input>';
// } else {
// return row[0];
// }
// }
// },
					     {
								"mData" : 0
						},
						{
							"mData" : 2
						}, {
							"mData" : 1
						}, {
							"mData" : 3
						}, {
							"mData" : 4
						},{
							"mData" : 7
						},{
							"mData" : 6
						},

						],

					});
// t.on('order.dt search.dt', function() {
// t.column(0, {
// search : 'applied',
// order : 'applied'
// }).nodes().each(function(cell, i) {
// cell.innerHTML = "BS" + 1000 + i;
// });
// }).draw();

					var table = $('#reportBookInHandTable').DataTable({
						"sAjaxSource" : "/bookshelfAdmin/booksinhandreport",
						"sAjaxDataProp" : "",
						"pagingType" : "full_numbers",
						"order" : [ [ 0, "asc" ] ],
						"aoColumns" : [ {
							"mData" : 0
						}, {
							"mData" : 6
						}, {
							"mData" : 1
						}, {
							"mData" : 2
						}, {
							"mData" : 3
						}, {
							"mData" : 5
						}, {
							"mData" : 9
						},

						]
					});

					var table = $('#reportBooksOutTable').DataTable({
						"sAjaxSource" : "/bookshelfAdmin/booksoutreport",
						"sAjaxDataProp" : "",
						"pagingType" : "full_numbers",
						"order" : [ [ 0, "asc" ] ],
						"aoColumns" : [ {
							"mData" : 0
						}, {
							"mData" : 10
						},
						{
							"mData" : 6
						},
						{
							"mData" : 1
						}, {
							"mData" : 2
						}, {
							"mData" : 3
						}, {
							"mData" : 5
						}, 
						{
							"mData" : 9
						},
						]
					});

					var table = $('#reportTodayDeliveryTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/todaydelivery",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													"mData" : 0
												},
												{
													"mData" : 1
												},
												{
													"mData" : 2
												},
												{
													"mData" : 3
												},

												{
													mRender : function(data,
															type, row) {
														var result = ''
																+ row[5] + ','
																+ row[6] + ','
																+ row[7] + ','
																+ row[8] + ','
																+ row[9];
														return result;

													}
												},
												{
													mRender : function(data,
															type, row) {
														return row[4];

													}
												},

										]
									});

					var table = $('#reportTodayPickupTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/todaypickup",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													"mData" : 0
												},
												{
													"mData" : 1
												},
												{
													"mData" : 2
												},
												{
													"mData" : 3
												},

												{
													mRender : function(data,
															type, row) {
														var result = ''
																+ row[5] + ','
																+ row[6] + ','
																+ row[7] + ','
																+ row[8] + ','
																+ row[9];
														return result;

													}
												},
												{
													mRender : function(data,
															type, row) {
														return row[4];

													}
												},

										]
									});
					var table = $('#reportNextDayDeliveryTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/nextdaydelivery",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													"mData" : 0
												},
												{
													"mData" : 1
												},
												{
													"mData" : 2
												},
												{
													"mData" : 3
												},

												{
													mRender : function(data,
															type, row) {
														var result = ''
														// + row[5] + ','
														+ row[6] + ',' + row[7]
																+ ',' + row[8]
																+ ',' + row[9];
														return result;

													}
												},
												{
													mRender : function(data,
															type, row) {
														return row[4];

													}
												},
												{
													mRender : function(data,
															type, row) {
														var myDate = new Date(
																row[16]);
														var date = myDate
																.getDate()
																+ "-"
																+ (myDate
																		.getMonth() + 1)
																+ "-"
																+ myDate
																		.getFullYear();
														return date;

													}
												},

										]
									});
					
					var table = $('#reportNextDayPickupTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/nextdaypickup",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													"mData" : 0
												},
												{
													"mData" : 1
												},
												{
													"mData" : 2
												},
												{
													"mData" : 3
												},

												{
													mRender : function(data,
															type, row) {
														var result = ''
																+ row[5] + ','
																+ row[6] + ','
																+ row[7] + ','
																+ row[8] + ','
																+ row[9];
														return result;

													}
												},
												{
													mRender : function(data,
															type, row) {
														return row[4];

													}
												},
												{
													mRender : function(data,
															type, row) {
														var myDate = new Date(
																row[16]);
														var date = myDate
																.getDate()
																+ "-"
																+ (myDate
																		.getMonth() + 1)
																+ "-"
																+ myDate
																		.getFullYear();
														return date;

													}
												},

										]

									});

					var table = $('#reportUndeliveredTable')
					.DataTable(
							{
								"sAjaxSource" : "/bookshelfAdmin/getundelivereditems",
								"sAjaxDataProp" : "",
								"pagingType" : "full_numbers",
								"order" : [ [ 0, "asc" ] ],
								"aoColumns" : [
										{
											"mData" : 0
										},
										{
											"mData" : 1
										},
										{
											"mData" : 2
										},
										{
											"mData" : 3
										},
										{
											"mData" : 18
										},
										{
											mRender : function(data,
													type, row) {
												var result = ''
														+ row[5] + ','
														+ row[6] + ','
														+ row[7] + ','
														+ row[8] + ','
														+ row[9];
												return result;

											}
										},
										{
											mRender : function(data,
													type, row) {
												return row[4];

											}
										},
										{
											"mData" : 17
										},

								]

							});		
					
					var table = $('#reportFullSubscribeUserTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/fullsubscribeuser",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										//"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													"mData" : 7
												},
												{
													"mData" : 8
												},
												{
													"mData" : 1
												},
												{
													"mData" : 13
												},
												
												{
													mRender : function(data,
															type, row) {
														var result = ''
																+ row[9] + ','
																+ row[10] + ','
																+ row[11] + ','
																+ row[12];
														return result;

													}
												},
												{
													"mData" : 2
												},
												{
													mRender : function(data,
															type, row) {
														var myDate = new Date(
																row[3]);
														var date = myDate
																.getDate()
																+ "-"
																+ (myDate
																		.getMonth() + 1)
																+ "-"
																+ myDate
																		.getFullYear();
														return date;

													}
												},

										]
									});

					var table = $('#reportOnlyRegisterUser')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/onlyregisteruser",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										//"order" : [ [ 0, "desc" ] ],
										"aoColumns" : [
												{
													"mData" : 0
												},
												{
													"mData" : 1
												},
												{
													"mData" : 2
												},
												{
													"mData" : 3
												},
												{
													mRender : function(data,
															type, row) {
														var myDate = new Date(
																row[4]);
														var date = myDate
																.getDate()
																+ "-"
																+ (myDate
																		.getMonth() + 1)
																+ "-"
																+ myDate
																		.getFullYear();
														return date;

													}
												},
												{
													mRender : function(data,
															type, row) {
														if(row[5] == "paybycash") {
															return "pay by cash";
														} else if(row[5] == "pending"){
															return "pay online";
														} else {
															return "only registered";
														}
													}
												}

										]
									});
					var table = $('#reportExpireUsersTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/expireusers",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													"mData" : 0
												},
												{
													"mData" : 1
												},
												{
													"mData" : 2
												},
												{
													mRender : function(data,
															type, row) {
														var myDate = new Date(
																row[3]);
														var date = myDate
																.getDate()
																+ "-"
																+ (myDate
																		.getMonth() + 1)
																+ "-"
																+ myDate
																		.getFullYear();
														return date;

													}
												}, ]
									});

					oTable = $('#confirmpickupTable')
							.dataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/confirmpickpbook",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													"mData" : 0
												},

												{
													"mData" : 16
												},
												{
													"mData" : 1
												},
												{
													"mData" : 2
												},
												{
													"mData" : 17
												},
												{
													"mData" : 3
												},
												{
													mRender : function(data,
															type, row) {
														var result = ''
																+ row[4] + ','
																+ row[5] + ','
																+ row[6] + ','
																+ row[7] + ','
																+ row[8];
														return result;

													}
												},
												{
													mRender : function(data,
															type, row) {

														if (row[18] == 0) {
															return '<input type="checkbox" id="pickupconfirm'
																	+ row[13]
																	+ '" name="pickupconfirm'
																	+ row[13]
																	+ '" value="'
																	+ row[13]
																	+ '" >Finally Confirm Pickup</input>';
														} else {
															return "Pickupcompleted";
														}

													}

												},

										]

									});

					var table = $('#reportPaymentTable')
							.DataTable(
									{
										"sAjaxSource" : "/bookshelfAdmin/paymentconfirmation",
										"sAjaxDataProp" : "",
										"pagingType" : "full_numbers",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [

												{
													mRender : function(data,
															type, row) {
														if (row[10] != null) {

															return
																	row[10],
																	'<input type="hidden" id="paymentuserid" name="paymentuserid" value="'
																			+ row[10]
																			+ '"> '
																			+ row[10]
																			+ '</input>';
														} else {
															return row[10];
														}
													}
												},
												{
													"mData" : 1
												},
												{
													mRender : function(data,
															type, row) {
														if (row[11] != null) {

															return '<input type="hidden" id="paymentsubid" name="paymentsubid" value="'
																	+ row[11]
																	+ '">'
																	+ row[11]
																	+ '</input>';
														} else {
															return row[11];
														}
													}
												},
												{
													mRender : function(data,
															type, row) {
														if (row[12] != null) {

															return '<input type="hidden" id="paymentamount" name="paymentamount" value="'
																	+ row[12]
																	+ '">'
																	+ row[12]
																	+ ' </input>';
														} else {
															return row[12];
														}
													}
												},
												{
													mRender : function(data,
															type, row) {
														if (row[13] != 'success') {
															return '<input type="checkbox" id="confirmpayment'
																	+ row[0]
																	+ '" name="confirmpayment'
																	+ row[0]
																	+ '" value="'
																	+ row[0]
																	+ '" >Payment Confirmation</input>';
														} else if (row[13] === 'success') {
															return row[13];
														} else {
															return "confirmstatus";
														}
													}
												}

										]
									});
					
					
					var table = $('#activateusersubscriptionTable')
					.DataTable(
							{
								"sAjaxSource" : "/bookshelfAdmin/activateusersubscriptions",
								"sAjaxDataProp" : "",
								"pagingType" : "full_numbers",
								"order" : [ [ 0, "asc" ] ],
								"aoColumns" : [
										{
											"mData" : 0

										},
										{
											"mData" : 1
										},
										{
											"mData" : 2
										},
										{
											"mData" : 3
										},
										{
											"mData" : 4
										},
										{
											"mData" : 5
										},
										{
											mRender : function(data,
													type, row) {
												return '<input type="checkbox" id="confirmcashpayment'
												+ row[0]
												+ '" name="confirmcashpayment'
												+ row[0]
												+ '" value="'
												+ row[0]
												+ '" >Payment Confirmation</input>';
												
											}
										}

								]
							});
					
					
					var table = $('#TestimonialsTable')
					.DataTable(
							{
								"sAjaxSource" : "/bookshelfAdmin/testimoialsreport",
								"sAjaxDataProp" : "",
								"pagingType" : "full_numbers",
								"order" : [ [ 0, "asc" ] ],
								"aoColumns" : [
										{
											"mData" : 0
										},
										{
											"mData" : 1
										},
										{
											mRender : function(data,
													type, row) {
												if (row[2] == 0) {
													return '<input type="checkbox" id="confirmtestimonial'
															+ row[3]
															+ '" name="confirmtestimonial'
															+ row[3]
															+ '" value="'
															+ row[3]
															+ '" >Tetimonial Confirmation</input>';
												}else {
													return "confirmstatus";
												}
											}
										},
										{
											mRender : function(data,
													type, row) {
												if (row[4] == 1) {
													return '<a class="table-edit" href="/bookshelfAdmin/deletetestimonial/'
															+ row[3]
															+ '/0"  >Active</a>'
												} else {
													return '<a class="table-edit" href="/bookshelfAdmin/deletetestimonial/'
															+ row[3]
															+ '/1"  >DeActive</a>'
												}
											}
										}

								]
							});
					
									});
