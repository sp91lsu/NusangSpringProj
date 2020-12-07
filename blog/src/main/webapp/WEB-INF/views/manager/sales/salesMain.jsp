<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp"%>
<link rel="stylesheet" href="/css/manager/sales.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

<div class="myContainer d-flex flex-column">
	<h3>매출 통계</h3>
	
	<div>
		<!-- Tab links -->
		<div class="tab">
		  <button class="tablinks" onclick="openCity(event, 'timeTab')">시간대별</button>
		  <button class="tablinks" onclick="openCity(event, 'dateTab')">일별</button>
		  <!-- <button class="tablinks" onclick="openCity(event, 'dayTab')">요일별</button>
		   -->
		   <button class="tablinks" onclick="openCity(event, 'monthTab')">월별</button>
		</div>
		
		<!-- Tab content -->
		<!-- 시간대별 -->	
		<div id="timeTab" class="tabcontent">
			<div class="d-flex flex-wrap align-items-center">
				<input name="date1" type="date"><img alt="tilde" src="/image/tilde.png"><input name="date2" type="date">
				<button class="readBtn btn btn-warning ml-auto">조회</button>
			</div>
			<div class="d-flex flex-column">
					<!-- 차트존 -->
					<div class="chartzone d-inline-flex">
						<div class="one">
						<!-- 첫번쨰 차트 -->
						<canvas id="myChart"></canvas>
						</div>
						<!-- 두번쨰 차트 -->
						<div class="two card">
							<div class="card-body">
								<canvas id="myChart1"></canvas>
							</div>
							<div class="card-footer text-center text-dark">
								<h3>Pie</h3>
							</div>
						</div>
					</div>
			</div>
		</div>
		<!-- 일별 -->
		<div id="dateTab" class="tabcontent">
		  <div class="d-flex flex-wrap align-items-center">
				<select id="years" name="years">
					<c:forEach var="i" begin="0" end="5">
					 	<c:set var="yearOption" value="${2020-i}" />
    					<option value="${yearOption}">${yearOption}년</option>
					</c:forEach>
				</select>
				<select id="months" name="months">
					<c:forEach var="num" begin="1" end="12">
						<option value="${num}">${num}월</option>
					</c:forEach>
				</select>
				<button class="readBtn btn btn-warning ml-auto">조회</button>
			</div>
			<div class="d-flex flex-column">
					<!-- 차트존 -->
					<div class="chartzone d-inline-flex">
						<div class="one">
						<!-- 첫번쨰 차트 -->
						<canvas id="myChart_date"></canvas>
						</div>
						<!-- 두번쨰 차트 -->
						<div class="two card">
							<div class="card-body">
								<canvas id="myChart1_date"></canvas>
							</div>
							<div class="card-footer text-center text-dark">
								<h3>Pie</h3>
							</div>
						</div>
					</div>
			</div>
		</div>
		<!-- 요일별 -->
		<!-- <div id="dayTab" class="tabcontent">
		  <div class="d-flex flex-wrap align-items-center">
				<input name="date1" type="date"><img alt="tilde" src="/image/tilde.png"><input name="date2" type="date">
				<button class="readBtn btn btn-warning ml-auto">조회</button>
			</div>
			<div class="d-flex flex-column">
					차트존
					<div class="chartzone d-inline-flex">
						<div class="one">
						첫번쨰 차트
						<canvas id="myChart"></canvas>
						</div>
						두번쨰 차트
						<div class="two card">
							<div class="card-body">
								<canvas id="myChart1"></canvas>
							</div>
							<div class="card-footer text-center text-dark">
								<h3>Pie</h3>
							</div>
						</div>
					</div>
			</div>
		</div>
		 -->
		 <!-- 월별 -->
		<div id="monthTab" class="tabcontent">
		  <div class="d-flex flex-wrap align-items-center">
				<select id="years2" name="years">
					<c:forEach var="i" begin="0" end="5">
					 	<c:set var="yearOption" value="${2020-i}" />
    					<option value="${yearOption}">${yearOption}년</option>
					</c:forEach>
				</select>
				<button class="readBtn btn btn-warning ml-auto">조회</button>
		  	 </div>
		  	 <div class="d-flex flex-column">
					<!-- 차트존 -->
					<div class="chartzone d-inline-flex">
						<div class="one">
						<!-- 첫번쨰 차트 -->
						<canvas id="myChart_month"></canvas>
						</div>
						<!-- 두번쨰 차트 -->
						<div class="two card">
							<div class="card-body">
								<canvas id="myChart1_month"></canvas>
							</div>
							<div class="card-footer text-center text-dark">
								<h3>Pie</h3>
							</div>
						</div>
					</div>
			</div>
		</div>
	</div>
	
</div>

<script type="text/javascript" src="/js/manager/sales.js"></script>
