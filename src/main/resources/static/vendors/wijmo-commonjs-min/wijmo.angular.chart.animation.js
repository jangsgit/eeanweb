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

"use strict";var __extends=this&&this.__extends||function(){var extendStatics=function(t,r){return(extendStatics=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(t,r){t.__proto__=r}||function(t,r){for(var n in r)r.hasOwnProperty(n)&&(t[n]=r[n])})(t,r)};return function(t,r){extendStatics(t,r);function __(){this.constructor=t}t.prototype=null===r?Object.create(r):(__.prototype=r.prototype,new __)}}(),__importStar=this&&this.__importStar||function(t){if(t&&t.__esModule)return t;var r={};if(null!=t)for(var n in t)Object.hasOwnProperty.call(t,n)&&(r[n]=t[n]);r.default=t;return r};Object.defineProperty(exports,"__esModule",{value:!0});var wijmo_angular_base_1=require("wijmo/wijmo.angular.base"),mNg=__importStar(require("angular")),wjcChartAnimation=__importStar(require("wijmo/wijmo.chart.animation")),wjNg=mNg,wijmoChartAnimationName="wj.chart.animation";exports.ngModuleName=wijmoChartAnimationName;var wijmoChartAnimation=wijmo_angular_base_1._registerNgModule(wijmoChartAnimationName);wijmo_angular_base_1.softRefChartAnimation()&&wijmoChartAnimation.directive("wjFlexChartAnimation",[function(){return new WjFlexChartAnimation}]);var WjFlexChartAnimation=function(t){__extends(WjFlexChartAnimation,t);function WjFlexChartAnimation(){var r=t.call(this)||this;r.require=["?^wjFlexChart","?^wjFlexPie","?^wjSunburst","?^wjFinancialChart","?^wjFlexRadar"];return r}Object.defineProperty(WjFlexChartAnimation.prototype,"_controlConstructor",{get:function(){return wjcChartAnimation.ChartAnimation},enumerable:!0,configurable:!0});return WjFlexChartAnimation}(wijmo_angular_base_1.WjDirective);exports.WjFlexChartAnimation=WjFlexChartAnimation;