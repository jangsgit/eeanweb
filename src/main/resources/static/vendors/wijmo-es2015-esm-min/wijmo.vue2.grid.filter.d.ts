/*!
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
/**
 * {@module wijmo.vue2.grid.filter}
 * Contains Vue 2 and 3 components for the <b>wijmo.grid.filter</b> module.
 */
/**
 *
 */
export declare var ___keepComment: any;
/**
 * Vue component for the {@link wijmo.grid.filter.FlexGridFilter} class.
 *
 * The <b>wj-flex-grid-filter</b> component should be contained in
 * a {@link wijmo.vue2.grid.WjFlexGrid} component.
 *
 * The component supports all properties and events of the pure JavaScript {@link wijmo.grid.filter.FlexGridFilter} class it represents.
 *
 * The component includes an <b>initialized</b> event that is raised when the control is initialized after it is added to the page.
 * You can use this event to perform further initialization in addition to setting properties in markup.
 * The signature of the handler function is the same as any other Wijmo event handlers.
 *
 * The example below shows how to instantiate and initialize a
 * {@link wijmo.grid.filter.FlexGridFilter} control with a filter using Vue markup:
 *
 * <pre>&lt;wj-flex-grid
 *   :items-source="data"&gt;
 *   &lt;wj-flex-grid-filter&gt;&lt;/wj-flex-grid-filter&gt;
 * &lt;/wj-flex-grid&gt;</pre>
 */
export declare var WjFlexGridFilter: any;
export declare function registerGridFilter(app: any): void;
