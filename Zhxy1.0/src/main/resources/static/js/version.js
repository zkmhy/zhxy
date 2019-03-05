var lists=[];

var version=new Vue({
	el:"#content",
	data:{
		list:[],
		v:null,
	},methods:{
		click:function(i){
			query(i);
		}
	}
});

$.ajax({
	url:URL+"allversion",
	success:function(e){
		version.list=e;
	}
});

function query(i){
	$.ajax({
		url:URL+"version",
		data:{
			id:i
		},success:function(e){
			version.v=e;
		}
	});
}

$(window).scroll(function(){
	var h=$(this).scrollTop();
	console.log($(".to").offset().top);
	var height=$("#crumb").offset().top;
	if(height<h){
		$(".info").slideUp(500);
		$(".to").slideDown(500);
	}else{
		$(".info").slideDown(500);
		$(".to").slideUp(500);
	}
});

$("#content").on("click",".to",function(){
	var id=$(this).attr("value");
	var height=$("[mid="+id+"]").offset().top;
	$("html,body").animate({scrollTop:(height-120)},800);
});
