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

"use strict";var __importStar=this&&this.__importStar||function(r){if(r&&r.__esModule)return r;var e={};if(null!=r)for(var t in r)Object.hasOwnProperty.call(r,t)&&(e[t]=r[t]);e.default=r;return e};Object.defineProperty(exports,"__esModule",{value:!0});const wijmo_knockout_base_1=require("wijmo/wijmo.knockout.base"),mKo=__importStar(require("knockout")),wjcFilter=__importStar(require("wijmo/wijmo.grid.filter"));var wjKo=mKo;class wjFlexGridFilter extends wijmo_knockout_base_1.WjBinding{_getControlConstructor(){return wjcFilter.FlexGridFilter}}exports.wjFlexGridFilter=wjFlexGridFilter;wjKo.bindingHandlers.wjFlexGridFilter=new wjFlexGridFilter;