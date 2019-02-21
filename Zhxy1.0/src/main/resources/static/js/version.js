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