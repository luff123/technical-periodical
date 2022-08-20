<template>
	<view class="page">
		<img :src="path"></img>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				"path": ""
			}
		},
		methods: {
			
		},
		onShow() {
			this.path = getApp().globalData.currentResourcePath;
			console.log(this.path);
		}
	}
</script>

<style>
	.page {
		width: 100%;
		height: 100%;
		background-color: #F7F7F7;
		/* display: flex;
		flex-direction: column;
		align-items: center; */
	}

	.page img {
		width: 100%;
		height: auto;
	}
</style>
