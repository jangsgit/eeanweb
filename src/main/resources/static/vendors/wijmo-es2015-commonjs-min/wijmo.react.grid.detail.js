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

"use strict";var __importStar=this&&this.__importStar||function(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var r in e)Object.hasOwnProperty.call(e,r)&&(t[r]=e[r]);t.default=e;return t};Object.defineProperty(exports,"__esModule",{value:!0});const wijmo_react_base_1=require("wijmo/wijmo.react.base"),ReactDOM=__importStar(require("react-dom")),wjcCore=__importStar(require("wijmo/wijmo")),wjcGridDetail=__importStar(require("wijmo/wijmo.grid.detail"));class FlexGridDetail extends wijmo_react_base_1.ComponentBase{constructor(e){super(e,wjcGridDetail.FlexGridDetailProvider);this._parentInCtor=!0;this._renderedCells=[];this._destroyCell=this._destroyCell.bind(this)}_onBeforeWillUnmount(e){super._onBeforeWillUnmount(e);this._unmountRenderedCells()}_initParent(){this._setTemplateRelatedProps(this.props);super._initParent()}componentDidUpdate(e){super.componentDidUpdate(e);this.props.template!==this._template&&this._setTemplateRelatedProps(this.props);this._template?this._renderedCells.forEach(e=>{const t=e.cell,r=this._template(this._getTemplateContext(e.row));ReactDOM.render(r,t)}):this._unmountRenderedCells()}_setTemplateRelatedProps(e){const t=this.control,r=this._template=e.template;if(r){t.createDetailCell=this._getCellCreator(r);t.disposeDetailCell=this._destroyCell}else{t.createDetailCell=e.createDetailCell;t.disposeDetailCell=e.disposeDetailCell}}_getTemplateContext(e){return{row:e,item:e.dataItem,provider:this.control}}_unmountRenderedCells(){this._renderedCells.forEach(e=>{ReactDOM.unmountComponentAtNode(e.cell)});this._renderedCells=[]}_getCellCreator(e){return t=>{const r=document.createElement("div"),l=e(this._getTemplateContext(t));ReactDOM.render(l,r);this._renderedCells.push({row:t,cell:r});return r}}_destroyCell(e){const t=this.control.grid.rows[e.index-1];let r=-1;this._renderedCells.some((e,l)=>e.row===t&&!!((r=l)+1));wjcCore.assert(-1!==r,"Main row rendered cell is not found");ReactDOM.unmountComponentAtNode(this._renderedCells[r].cell);this._renderedCells.splice(r,1);return!0}}exports.FlexGridDetail=FlexGridDetail;