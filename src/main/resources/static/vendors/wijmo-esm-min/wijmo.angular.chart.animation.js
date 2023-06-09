﻿/*!
    *
    * Wijmo Library 5.20221.842
    * http://wijmo.com/
    *
    * Copyright(c) GrapeCity, Inc.  All rights reserved.
    *
    * Licensed under the GrapeCity Commercial License.
    * sales@wijmo.com
    * wijmo.com/products/wijmo-5/license/
    *
    */

var __extends=this&&this.__extends||function(){var extendStatics=function(t,n){return(extendStatics=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(t,n){t.__proto__=n}||function(t,n){for(var r in n)n.hasOwnProperty(r)&&(t[r]=n[r])})(t,n)};return function(t,n){extendStatics(t,n);function __(){this.constructor=t}t.prototype=null===n?Object.create(n):(__.prototype=n.prototype,new __)}}();import{WjDirective,_registerNgModule,softRefChartAnimation}from"wijmo/wijmo.angular.base";import*as mNg from"angular";import*as wjcChartAnimation from"wijmo/wijmo.chart.animation";var wjNg=mNg,wijmoChartAnimationName="wj.chart.animation";export var ngModuleName=wijmoChartAnimationName;var wijmoChartAnimation=_registerNgModule(wijmoChartAnimationName);softRefChartAnimation()&&wijmoChartAnimation.directive("wjFlexChartAnimation",[function(){return new WjFlexChartAnimation}]);var WjFlexChartAnimation=function(t){__extends(WjFlexChartAnimation,t);function WjFlexChartAnimation(){var n=t.call(this)||this;n.require=["?^wjFlexChart","?^wjFlexPie","?^wjSunburst","?^wjFinancialChart","?^wjFlexRadar"];return n}Object.defineProperty(WjFlexChartAnimation.prototype,"_controlConstructor",{get:function(){return wjcChartAnimation.ChartAnimation},enumerable:!0,configurable:!0});return WjFlexChartAnimation}(WjDirective);export{WjFlexChartAnimation};