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

import{VueApi,WjComponentBehavior}from"wijmo/wijmo.vue2.base";import*as wjcGridGrouppanel from"wijmo/wijmo.grid.grouppanel";class WjGroupPanelBehavior extends WjComponentBehavior{}WjGroupPanelBehavior.tag="wj-group-panel";WjGroupPanelBehavior.props=["isDisabled","tabOrder","hideGroupedColumns","showDragGlyphs","maxGroups","placeholder","filter","groupDescriptionCreator","grid"];WjGroupPanelBehavior.events=["initialized","gotFocus","lostFocus","refreshing","refreshed","invalidInput"];WjGroupPanelBehavior.classCtor=function(){return wjcGridGrouppanel.GroupPanel};export var WjGroupPanel=WjGroupPanelBehavior.register();function registerV3WjGroupPanel(r){r.component(WjGroupPanelBehavior.tag,WjGroupPanel)}export function registerGridGrouppanel(r){VueApi.isV3Plus&&registerV3WjGroupPanel(r)}