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

"use strict";var __extends=this&&this.__extends||function(){var extendStatics=function(t,i){return(extendStatics=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(t,i){t.__proto__=i}||function(t,i){for(var n in i)i.hasOwnProperty(n)&&(t[n]=i[n])})(t,i)};return function(t,i){extendStatics(t,i);function __(){this.constructor=t}t.prototype=null===i?Object.create(i):(__.prototype=i.prototype,new __)}}(),__importStar=this&&this.__importStar||function(t){if(t&&t.__esModule)return t;var i={};if(null!=t)for(var n in t)Object.hasOwnProperty.call(t,n)&&(i[n]=t[n]);i.default=t;return i};Object.defineProperty(exports,"__esModule",{value:!0});var wijmo_vue2_base_1=require("wijmo/wijmo.vue2.base"),wjcChartAnimation=__importStar(require("wijmo/wijmo.chart.animation")),WjFlexChartAnimationBehavior=function(t){__extends(WjFlexChartAnimationBehavior,t);function WjFlexChartAnimationBehavior(){return null!==t&&t.apply(this,arguments)||this}WjFlexChartAnimationBehavior.tag="wj-flex-chart-animation";WjFlexChartAnimationBehavior.parentInCtor=!0;WjFlexChartAnimationBehavior.props=["animationMode","easing","duration","axisAnimation"];WjFlexChartAnimationBehavior.events=["initialized"];WjFlexChartAnimationBehavior.classCtor=function(){return wjcChartAnimation.ChartAnimation};return WjFlexChartAnimationBehavior}(wijmo_vue2_base_1.WjComponentBehavior);exports.WjFlexChartAnimation=WjFlexChartAnimationBehavior.register();function registerV3WjFlexChartAnimation(t){t.component(WjFlexChartAnimationBehavior.tag,exports.WjFlexChartAnimation)}function registerChartAnimation(t){wijmo_vue2_base_1.VueApi.isV3Plus&&registerV3WjFlexChartAnimation(t)}exports.registerChartAnimation=registerChartAnimation;