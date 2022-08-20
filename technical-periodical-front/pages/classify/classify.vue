<template>
	<view class="page">
		
		<view class="choice">
			<button ref="periodicalChoice" @click="choosePeriodical()">期刊</button>
			<button ref="paperChoice" @click="choosePaper()">文章</button>
		</view>
		
		<view class="search">
			<input @input="changeKey()" type="text" placeholder="请输入关键词" ref="keyWord"/>
			<button @click="search()">搜索</button>
		</view>
		
		<view class="date">
			
			<view class="start-date">
				<span>开始日期：</span>
				<view class="start-year">
					<select ref="startYear" name="startYear" @change="changeStartYear()">
						<option v-for="year in yearRandom" :value="year">{{ year }}</option>
					</select>
				</view>
				<view class="start-month">
					<select ref="startMonth" name="startMonth" @change="changeStartMonth()">
						<option v-for="month in monthRandom" :value="month">{{ month }}</option>
					</select>
				</view>
				<view class="start-day">
					<select ref="startDay" name="startDay" @change="changeStartDay()">
						<option v-for="day in dayRandom" :value="day">{{ day }}</option>
					</select>
				</view>
			</view>
			
			<view class="start-date">
				<span>结束日期：</span>
				<view class="end-year">
					<select ref="endYear" name="endYear" @change="changeEndYear()">
						<option v-for="year in yearRandom" :value="year">{{ year }}</option>
					</select>
				</view>
				<view class="end-month">
					<select ref="endMonth" name="endMonth" @change="changeEndMonth()">
						<option v-for="month in monthRandom" :value="month">{{ month }}</option>
					</select>
				</view>
				<view class="end-day">
					<select ref="endDay" name="endDay" @change="changeEndDay()">
						<option v-for="day in dayRandom" :value="day">{{ day }}</option>
					</select>
				</view>
			</view>
			
		</view>
		
		<view v-show="choice == 'periodical'" class="periodical-classify">
			<span>分类：</span>
			<select ref="periodicalClassify" name="classify" @change="changePeriodicalClassify()">
				<option value="全部">全部</option>
				<option value="医学">医学</option>
			</select>
		</view>
		
		<view v-show="choice == 'paper'" class="periodical-classify" @change="changePaperClassify()">
			<span>分类：</span>
			<select ref="paperClassify" name="classify">
				<option value="全部">全部</option>
				<option value="现代医学">现代医学</option>
				<option value="中医药">中医药</option>
				<option value="人体生物学">人体生物学</option>
			</select>
		</view>
		
		<hr style="width: 95%; background-color: #999999; margin-top: 20rpx;">
		
		<view v-show="choice == 'periodical'" class="periodicals">
			<view v-for="periodical in periodicals">
				<view class="periodical" @tap="tapPeriodical(periodical.id)">
					<image :src="prefix + '/' + periodical.coverPath"></image>
					<view class="periodical-content">
						<text>{{ periodical.name }}</text>
						<br>
						<text>创立者：{{ periodical.author }}</text>
						<br>
						<text>创立日期：{{ periodical.date }}</text>
						<br>
						<hr>
						<text>简介：</text>
						<text>{{ periodical.summary.substr(0, 69) }}{{ periodical.summary.length > 69 ? "..." : "" }}</text>
					</view>
				</view>
			</view>
		</view>
		
		<view v-show="choice == 'paper'" class="papers">
			<view v-for="paper in papers">
				<view class="paper" @tap="tapPaper(paper.id)">
					<image :src="prefix + '/' + paper.coverPath"></image>
					<view class="paper-main">
						<text>{{ paper.title.substr(0, 13) }}{{ paper.title.length > 13 ? "..." : "" }}</text>
						<br>
						<text>作者：{{ paper.author }}</text>
						<text>{{ paper.date }}</text>
						<hr>
						<text>{{ paper.summary.substr(0, 58) }}{{ paper.summary.length > 58 ? "..." : "" }}</text>
						<br>
					</view>
				</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				"prefix": "",
				"choice": "periodical",
				"periodicals": [],
				"papers": [],
				"yearRandom": [],
				"monthRandom": [],
				"dayRandom": [],
				"startYear": 1998,
				"startMonth": 1,
				"startDay": 1,
				"endYear": 1998,
				"endMonth": 1,
				"endDay": 1,
				"classify": "全部",
				"keyword": ""
			}
		},
		methods: {
			changeStartYear() {
				this.$nextTick(function(){
					this.startYear = this.$refs.startYear[this.$refs.startYear.options.selectedIndex].value;
					console.log(this.startYear);
				});
			},
			changeStartMonth() {
				this.$nextTick(function(){
					this.startMonth = this.$refs.startMonth[this.$refs.startMonth.options.selectedIndex].value;
				});
			},
			changeStartDay() {
				this.$nextTick(function(){
					this.startDay = this.$refs.startDay[this.$refs.startDay.options.selectedIndex].value;
				});
			},
			changeEndYear() {
				this.$nextTick(function(){
					this.endYear = this.$refs.endYear[this.$refs.endYear.options.selectedIndex].value;
				});
			},
			changeEndMonth() {
				this.$nextTick(function(){
					this.endMonth = this.$refs.endMonth[this.$refs.endMonth.options.selectedIndex].value;
				});
			},
			changeEndDay() {
				this.$nextTick(function(){
					this.endDay = this.$refs.endDay[this.$refs.endDay.options.selectedIndex].value;
				});
			},
			changePeriodicalClassify() {
				this.$nextTick(function(){
					this.classify = this.$refs.periodicalClassify[this.$refs.periodicalClassify.options.selectedIndex].value;
					console.log(this.classify);
				});
			},
			changePaperClassify() {
				this.$nextTick(function(){
					this.classify = this.$refs.paperClassify[this.$refs.paperClassify.options.selectedIndex].value;
				});
				console.log(this.classify);
			},
			choosePeriodical() {
				this.choice = "periodical";
				this.$nextTick(function(){
					this.$refs.periodicalChoice.$el.style.backgroundColor = "#aaaaaa";
					this.$refs.paperChoice.$el.style.backgroundColor = "#f7f7f7";
				});
			},
			choosePaper() {
				this.choice = "paper";
				this.$nextTick(function(){
					this.$refs.periodicalChoice.$el.style.backgroundColor = "#f7f7f7";
					this.$refs.paperChoice.$el.style.backgroundColor = "#aaaaaa";
				});
			},
			changeKey() {
				this.$nextTick(function(){
					this.keyword = this.$refs.keyWord.valueSync;
					console.log(this.keyword);
				});
			},
			search() {
				var checkDate = function(year, month, day) {
					console.log(year + ":" + month + ":" + day);
					if (day < 1) return false;
					if (month == 2) {
						if ((year % 100 == 0 && year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)) {
							return day <= 29;
						}
						else {
							return day <= 28;
						}
					}
					else if (month == 4 || month == 6 || month == 9 || month == 11) {
						return day <= 30
					}
					else {
						return day <= 31;
					}
				};
				var choice = this.choice;
				var keyword = this.keyword;
				var startDate = this.startYear + "-" + this.startMonth + "-" + this.startDay;
				var endDate = this.endYear + "-" + this.endMonth + "-" + this.endDay;
				var classify = this.classify;
				if (!checkDate(this.startYear, this.startMonth, this.startDay)
					|| !checkDate(this.endYear, this.endMonth, this.endDay))
				{
					uni.showModal({
						content: '日期不合法！',
						showCancel: false
					});
				}
				else if (choice == "paper") {
					uni.request({
						url: this.contextPath + "/search_paper?keyword=" + keyword + "&startDate=" + startDate + "&endDate=" + endDate + "&classify=" + classify,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							console.log(res);
							this.papers = res.data;
						}
					});
				}
				else if (choice == "periodical") {
					uni.request({
						url: this.contextPath + "/search_periodical?keyword=" + keyword + "&startDate=" + startDate + "&endDate=" + endDate + "&classify=" + classify,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							console.log(res.data);
							this.periodicals = res.data;
						}
					});
				}
				else {
					uni.showModal({
						content: '出现未知错误！',
						showCancel: false
					});
				}
			},
			tapPaper: function(id) {
				uni.request({
					url: this.contextPath + "/paper?id=" + id + "&userId=" + getApp().globalData.user.id,
					method: "POST",
					header: {
						"content-type": "application/x-www-form-urlencoded"
					},
					success: (res) => {
						console.log(res);
						getApp().globalData.PaperEncapsulation = res.data;
						uni.navigateTo({
							url: "../paper/paper"
						});
					}
				});
			},
			tapPeriodical(id) {
				uni.request({
					url: this.contextPath + "/periodical?id=" + id + "&userId=" + getApp().globalData.user.id,
					method: "POST",
					header: {
						"content-type": "application/x-www-form-urlencoded"
					},
					success: (res) => {
						console.log(res);
						getApp().globalData.PeriodicalEncapsulation = res.data;
						uni.navigateTo({
							url: "../periodical/periodical"
						});
					}
				});
			}
		},
		onShow() {
			this.prefix = this.contextPath;
			for (let i = 1998; i <= 2048; i++) this.yearRandom.push(i);
			for (let i = 1; i <= 12; i++) this.monthRandom.push(i);
			for (let i = 1; i <= 31; i++) this.dayRandom.push(i);
		}
	}
</script>

<style>
	.page {
		width: 100%;
		height: 100%;
		background-color: #f7f7f7;
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	.choice {
		display: flex;
		flex-direction: row;
		width: 100%;
	}
	.choice button {
		width: 50%;
	}
	.choice button:nth-child(1) {
		background-color: #aaaaaa;
	}
	.search {
		width: 90%;
		display: flex;
		flex-direction: row;
		align-items: center;
		margin-top: 20rpx;
		/* background-color: #C0C0C0; */
	}
	.search input {
		font-size: 30rpx;
		padding: 20rpx 20rpx;
		width: 70%;
		border: 1rpx solid black;
		border-radius: 20rpx 0 0 20rpx
	}
	.search button {
		width: 25%;
		background-color: #007AFF;
		border-radius: 0 20rpx 20rpx 0;
	}
	.start-date {
		display: flex;
		flex-direction: row;
	}
	.start-year {}
	.start-year select {}
	.start-year option {}
	.periodical-classify {
		margin-top: 20rpx;
		/* background-color: #C0C0C0; */
		width: 70%;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: space-around;
	}
	.periodical-classify span {
		font-size: 35rpx;
	}
	.periodical-classify select {
		width: 70%;
		font-size: 35rpx;
		padding: 10rpx;
		border-radius: 20rpx;
	}
	.periodicals {
		margin-top: 20rpx;
		width: 100%;
	}
	.periodical {
		width: 100%;
		height: 300rpx;
		display: flex;
		flex-direction: row;
		margin-top: 10rpx;
		justify-content: space-between;
		background-color: #F1F1F1;
		border-radius: 10rpx;
	}
	.periodical image {
		width: 160rpx;
		height: 228rpx;
		background-color: #999999;
		margin-left: 20rpx;
		margin-top: 40rpx;
		border-radius: 20rpx;
	}
	.periodical-content {
		width: 560rpx;
	}
	.periodical-content text:nth-child(1) {
		font-size: 40rpx;
		font-weight: bold;
		color: #444455;
	}
	.periodical-content text:nth-child(3) {}
	.periodical-content text:nth-child(5) {
		font-size: 25rpx;
	}
	.periodical-content text:nth-child(8) {
		font-weight: bold;
	}
	.periodical-content text:nth-child(9) {
		font-family: "kaiti";
	}
	.periodical-content hr {
		margin-top: 5rpx;
		background-color: #999999;
		width: 95%;
	}
	.papers {
		margin-top: 20rpx;
		width: 100%;
	}
	.paper {
		width: 100%;
		height: 265rpx;
		display: flex;
		flex-direction: row;
		margin-top: 10rpx;
		justify-content: space-between;
		background-color: #F1F1F1;
		border-radius: 10rpx;
	}
	.paper image {
		width: 160rpx;
		height: 228rpx;
		background-color: #999999;
		margin-left: 20rpx;
		margin-top: 20rpx;
		border-radius: 20rpx;
	}
	.paper-main {
		width: 550rpx;
	}
	.paper-main text:nth-child(1) {
		font-weight: bold;
		font-size: 40rpx;
		color: #333333;
	}
	.paper-main text:nth-child(3) {
		font-size: 27rpx;
	}
	.paper-main text:nth-child(4) {
		float: right;
		font-size: 27rpx;
		margin-right: 20rpx;
		font-weight: lighter;
	}
	.paper-main text:nth-child(6) {
		font-size: 37rpx;
		height: 50rpx;
		font-family: "kaiti";
	}
	.paper-main hr {
		background-color: #999999;
		width: 95%;
	}
	
</style>
