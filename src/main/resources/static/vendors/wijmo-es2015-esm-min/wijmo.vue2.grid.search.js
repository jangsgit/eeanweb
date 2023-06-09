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

import{VueApi,WjComponentBehavior}from"wijmo/wijmo.vue2.base";import*as wjcGridSearch from"wijmo/wijmo.grid.search";class WjFlexGridSearchBehavior extends WjComponentBehavior{}WjFlexGridSearchBehavior.tag="wj-flex-grid-search";WjFlexGridSearchBehavior.props=["isDisabled","tabOrder","text","delay","searchAllColumns","placeholder","cssMatch","grid"];WjFlexGridSearchBehavior.events=["initialized","gotFocus","lostFocus","refreshing","refreshed","invalidInput"];WjFlexGridSearchBehavior.classCtor=function(){return wjcGridSearch.FlexGridSearch};export var WjFlexGridSearch=WjFlexGridSearchBehavior.register();function registerV3WjFlexGridSearch(e){e.component(WjFlexGridSearchBehavior.tag,WjFlexGridSearch)}export function registerGridSearch(e){VueApi.isV3Plus&&registerV3WjFlexGridSearch(e)}