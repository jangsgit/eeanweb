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

"use strict";var __importStar=this&&this.__importStar||function(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var r in e)Object.hasOwnProperty.call(e,r)&&(t[r]=e[r]);t.default=e;return t};Object.defineProperty(exports,"__esModule",{value:!0});const wijmo_react_base_1=require("wijmo/wijmo.react.base"),wjcOlap=__importStar(require("wijmo/wijmo.olap"));class PivotGrid extends wijmo_react_base_1.ComponentBase{constructor(e){super(e,wjcOlap.PivotGrid,{objectProps:["childItemsPath","mergeManager","itemsSource","virtualizationThreshold","columnGroups"]})}}exports.PivotGrid=PivotGrid;class PivotChart extends wijmo_react_base_1.ComponentBase{constructor(e){super(e,wjcOlap.PivotChart,{objectProps:["itemsSource","headerStyle","footerStyle"]})}}exports.PivotChart=PivotChart;class PivotPanel extends wijmo_react_base_1.ComponentBase{constructor(e){super(e,wjcOlap.PivotPanel,{objectProps:["engine","itemsSource"]})}}exports.PivotPanel=PivotPanel;class Slicer extends wijmo_react_base_1.ComponentBase{constructor(e){super(e,wjcOlap.Slicer,{objectProps:["field"]})}}exports.Slicer=Slicer;