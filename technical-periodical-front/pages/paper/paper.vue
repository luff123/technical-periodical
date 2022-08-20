<template>
	<view id="page" class="page">
		<view class="title">
			<text>{{ paper.paperModel.title }}</text>
		</view>
		<view class="author">
			<text>作者：{{ paper.paperModel.author }}</text>
		</view>
		<view class="date">
			<text>日期：{{ paper.paperModel.date }}</text>
		</view>
		<view class="periodical">
			<text>期刊：{{ paper.paperModel.periodical }}</text>
		</view>
		<view class="classify">
			<text>类型：{{ paper.paperModel.classify }}</text>
		</view>
		<view class="abstract">
			<text style="font-weight: bold;">摘要：</text>
			<text>{{ paper.paperModel.summary }}</text>
		</view>
		<br>
		<view class="content">
			<!-- <text>{{ paper.paperModel.content }}</text> -->
			<p v-html="paper.paperModel.content">{{ paper.paperModel.content }}</p>
		</view>
		
		<hr style="width: 95%; background-color: #a4a4a4;margin: 20rpx 0;">
		
		<view class="end">
			<button @click="star">{{ paper.isStar ? "取消收藏" : "收藏" }}</button>
			<button @click="download()">下载</button>
		</view>
		
		<hr style="width: 95%; background-color: #a4a4a4;margin: 20rpx 0;">
		
		<view class="comments">
			<view class="comment-btn"><button @click="comment()">评论</button></view>
			
			<view v-for="comment in paper.paperModel.commentModels">
				<view class="comment" @tap="reply(comment.id)">
					<view class="comment-user">{{ comment.user }}</view>
					<view class="comment-date">{{ comment.date }}</view>
					<view class="comment-content">{{ comment.content }}</view>
					<hr style="width: 95%; background-color: #a4a4a4;margin: 20rpx 0;">
					<view class="replies">
						<view v-for="reply in comment.replyModels">
							<view class="reply">
								<view class="reply-user">{{ reply.user }}</view>
								<view class="reply-date">{{ reply.date }}</view>
								<view class="reply-content">{{ reply.content }}</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		
		<view class="shelter" v-show="showInputBox"></view>
		<view class="input-box" v-show="showInputBox">
			<textarea value="" placeholder="快来发表评论吧..." ref="content"/>
			<view class="input-box-btns">
				<button @click="cancelInput()">取消</button>
				<button @click="sendContent()">发送</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				"paper": {},
				"commentTest": [
					{
						"user": "alex",
						"date": "2021-03-04 15:43:25",
						"content": "真是一篇让人受益匪浅的好文章",
						"replyModels": [
							{ "user": "quan", "date": "2021-03-04 15:43:25", "content": "啊对对对"},
							{ "user": "huang", "date": "2021-03-04 15:43:25", "content": "你在肝肾魔"},
							{ "user": "sss", "date": "2021-03-04 15:43:25", "content": "理中客？"},
							{ "user": "aaa", "date": "2021-03-04 15:43:25", "content": "我也觉得。。。"}
						]
					},
					{
						"user": "Tom",
						"date": "2021-04-02 15:43:25",
						"content": "贝姐好好看！！！",
						"replyModels": [
							{ "user": "Tom", "date": "2021-04-02 15:51:25", "content": "打破零回复"}
						]
					},
					{
						"user": "Cris",
						"date": "2021-05-02 15:43:25",
						"content": "垃圾文章，狗都不看！",
						"replyModels": [
						]
					}
				],
				"showInputBox": false,
				"currentCommentId": -1,
				"sendType": "comment"
			}
		},
		methods: {
			star: function() {
				if (getApp().globalData.user.id == "0") {
					uni.navigateTo({
						url: "../login/login"
					});
				}
				else if (this.paper.isStar == false) {
					uni.request({
						url: this.contextPath + "/star?userId=" + getApp().globalData.user.id + "&paperId=" + this.paper.paperModel.id,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							// console.log(res);
							this.paper.isStar = true;
						}
					});
				}
				else {
					uni.request({
						url: this.contextPath + "/dis_star?userId=" + getApp().globalData.user.id + "&paperId=" + this.paper.paperModel.id,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							// console.log(res);
							this.paper.isStar = false;
						}
					});
				}
			},
			comment() {
				if (getApp().globalData.user.id == "0") {
					uni.navigateTo({
						url: "../login/login"
					});
				}
				else {
					this.sendType = "comment";
					this.showInputBox = !this.showInputBox;
				}
			},
			reply(id) {
				if (getApp().globalData.user.id == "0") {
					uni.navigateTo({
						url: "../login/login"
					});
				}
				else {
					this.currentCommentId = id;
					this.sendType = "reply";
					this.showInputBox = true;
				}
			},
			cancelInput() {
				this.showInputBox = false;
			},
			sendContent() {
				if (this.sendType == "comment") {
					this.$nextTick(function(){
						var userId = getApp().globalData.user.id;
						var paperId = this.paper.paperModel.id;
						var dt = new Date();
						var date = dt.getFullYear() + "-" + (dt.getMonth() + 1) + "-" + dt.getDate() + " " + dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
						var content = this.$refs.content.valueComposition;
						uni.request({
							url: this.contextPath + "/comment?userId=" + userId + "&paperId=" + paperId + "&date=" + date + "&content=" + content,
							method: "POST",
							header: {
								"content-type": "application/x-www-form-urlencoded"
							},
							success: (res) => {
								// console.log(res.data);
								this.showInputBox = false;
							}
						});
					});
				}
				else if (this.sendType == "reply") {
					this.$nextTick(function(){
						var userId = getApp().globalData.user.id;
						var commentId = this.currentCommentId;
						var dt = new Date();
						var date = dt.getFullYear() + "-" + (dt.getMonth() + 1) + "-" + dt.getDate() + " " + dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
						var content = this.$refs.content.valueComposition;
						uni.request({
							url: this.contextPath + "/reply?userId=" + userId + "&commentId=" + commentId + "&date=" + date + "&content=" + content,
							method: "POST",
							header: {
								"content-type": "application/x-www-form-urlencoded"
							},
							success: (res) => {
								// console.log(res.data);
								this.showInputBox = false;
							}
						});
					});
				}
				else {
					uni.showModal({
						content: '出现未知错误！！',
						showCancel: false
					});
				}
			},
			download: function() {
				// if (getApp().globalData.user.id == "0")
				// 	uni.navigateTo({ url: "../login/login" });
				// else {
					var fileUrl = this.contextPath + '/papers/' + this.paper.paperModel.id + '/download.png';
					console.log('download: ' + this.paper.paperModel.title);
					getApp().globalData.currentResourcePath = fileUrl;
					uni.navigateTo({
						url: "../download/download"
					});
				// }
				
			}
		},
		onShow() {
			this.paper = getApp().globalData.PaperEncapsulation;
			// console.log(this.paper.paperModel);
			// console.log(this.paper.isStar);
		}
	}
</script>

<style>
	.page {
		width: 100%;
		height: 100%;
		background-color: #f7f7f7;
		/* position: absolute; */
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	.title {
		font-size: 50rpx;
		font-weight: bold;
		text-align: center;
		margin: 20rpx 0;
	}
	.author {
		text-align: center;
		font-size: 30rpx;
		margin: 5rpx 0;
	}
	.date {
		text-align: center;
		font-size: 25rpx;
		font-weight: lighter;
		margin: 5rpx 0;
	}
	.periodical {
		text-align: center;
		font-size: 35rpx;
		margin: 10rpx 0;
	}
	.abstract {
		width: 86%;
	}
	.abstract text:nth-child(2) {
		font-size: 27rpx;
	}
	.content {
		width: 94%;
	}
	.end {
		width: 100%;
		display: flex;
		flex-direction: row;
		justify-content: space-around;
	}
	.comments {
		width: 100%;
		background-color: #F1F1F1;
	}
	.comment-btn {
		width: 100%;
	}
	.comment-btn button {
		font-size: 30rpx;
	}
	.comment {
		background-color: #E1E1E1;
		margin: 20rpx 0;
		padding: 10rpx 10rpx;
	}
	.comment-user {
		font-weight: bold;
		font-size: 38rpx;
	}
	.comment-date {
		font-weight: lighter;
		font-size: 25rpx;
	}
	.comment-content {
		margin-top: 5rpx;
	}
	.replies {
		margin-left: 40rpx;
		/* background-color: #C8C7CC; */
	}
	.reply {
		/* background-color: #AAAAAA; */
		margin: 10rpx 0;
	}
	.reply-user {
		font-weight: bold;
		font-size: 32rpx;
	}
	.reply-date {
		font-weight: lighter;
		font-size: 25rpx;
	}
	.reply-content {
		margin-top: 5rpx;
	}
	.shelter {
		position: fixed;
		height: 100%;
		width: 100%;
		background-color: #000000;
		opacity: 0.2;
	}
	.input-box {
		position: fixed;
		width: 80%;
		height: 410rpx;
		background-color: #FFFFFF;
		margin-top: 100rpx;
		border-radius: 20rpx;
	}
	.input-box textarea {
		margin: 20rpx 20rpx;
		height: 280rpx;
		background-color: #F1F1F1;
		padding: 5rpx 10rpx;
	}
	.input-box-btns {
		width: 100%;
		display: flex;
		flex-direction: row;
	}
	.input-box-btns button {
		height: 70rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
	}
	.input-box-btns button:nth-child(2) {
		background-color: #00afff;
		width: 50%;
	}
	
</style>
