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

"use strict";var __importStar=this&&this.__importStar||function(e){if(e&&e.__esModule)return e;var a={};if(null!=e)for(var r in e)Object.hasOwnProperty.call(e,r)&&(a[r]=e[r]);a.default=e;return a};Object.defineProperty(exports,"__esModule",{value:!0});const wijmo_react_base_1=require("wijmo/wijmo.react.base"),wjcGauge=__importStar(require("wijmo/wijmo.gauge"));class LinearGauge extends wijmo_react_base_1.ComponentBase{constructor(e){super(e,wjcGauge.LinearGauge)}}exports.LinearGauge=LinearGauge;class BulletGraph extends wijmo_react_base_1.ComponentBase{constructor(e){super(e,wjcGauge.BulletGraph)}}exports.BulletGraph=BulletGraph;class RadialGauge extends wijmo_react_base_1.ComponentBase{constructor(e){super(e,wjcGauge.RadialGauge,{objectProps:["needleElement"]})}}exports.RadialGauge=RadialGauge;class Range extends wijmo_react_base_1.ComponentBase{constructor(e){super(e,wjcGauge.Range);this._parentProp="ranges"}}exports.Range=Range;