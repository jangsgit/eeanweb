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

"use strict";var __importStar=this&&this.__importStar||function(r){if(r&&r.__esModule)return r;var e={};if(null!=r)for(var o in r)Object.hasOwnProperty.call(r,o)&&(e[o]=r[o]);e.default=r;return e};Object.defineProperty(exports,"__esModule",{value:!0});const wijmo_react_base_1=require("wijmo/wijmo.react.base"),wjcGridTransposed=__importStar(require("wijmo/wijmo.grid.transposed"));class TransposedGrid extends wijmo_react_base_1.ComponentBase{constructor(r){super(r,wjcGridTransposed.TransposedGrid,{objectProps:["childItemsPath","mergeManager","itemsSource","virtualizationThreshold","columnGroups","rowGroups"]})}}exports.TransposedGrid=TransposedGrid;class TransposedGridRow extends wijmo_react_base_1.ComponentBase{constructor(r){super(r,wjcGridTransposed.TransposedGridRow,{objectProps:["dataMap","cellTemplate","editor"]});this._parentProp="_rowInfo"}_initParent(){var r=this.parent.control;if(r.autoGenerateRows){r.autoGenerateRows=!1;r._rowInfo.clear()}super._initParent()}}exports.TransposedGridRow=TransposedGridRow;