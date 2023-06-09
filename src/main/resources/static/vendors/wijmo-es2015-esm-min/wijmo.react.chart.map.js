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

import{ComponentBase}from"wijmo/wijmo.react.base";import*as wjcChartMap from"wijmo/wijmo.chart.map";export class FlexMap extends ComponentBase{constructor(t){super(t,wjcChartMap.FlexMap,{objectProps:["palette","plotMargin","footerStyle","headerStyle","itemsSource","center"]})}componentDidMount(){let t=super.componentDidMount();this._setExtra(this.props);return t}componentDidUpdate(t){super.componentDidUpdate(t);this._setExtra(this.props)}_setExtra(t){"tooltipContent"in t&&(this.control.tooltip.content=t.tooltipContent)}}export class ScatterMapLayer extends ComponentBase{constructor(t){super(t,wjcChartMap.ScatterMapLayer,{objectProps:["style","itemsSource"]});this._parentProp="layers";this._siblingId="layers"}}export class GeoMapLayer extends ComponentBase{constructor(t){super(t,wjcChartMap.GeoMapLayer,{objectProps:["style","itemsSource","itemFormatter"]});this._parentProp="layers";this._siblingId="layers"}}export class GeoGridLayer extends ComponentBase{constructor(t){super(t,wjcChartMap.GeoGridLayer,{objectProps:["style","itemsSource"]});this._parentProp="layers";this._siblingId="layers"}}export class ColorScale extends ComponentBase{constructor(t){super(t,wjcChartMap.ColorScale,{objectProps:["colors"]});this._parentProp="colorScale"}}