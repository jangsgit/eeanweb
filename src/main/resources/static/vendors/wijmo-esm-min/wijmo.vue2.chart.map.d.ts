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
 * {@module wijmo.vue2.chart.map}
 * Contains Vue 2 and 3 components for the <b>wijmo.chart.map</b> module.
 */
/**
 *
 */
export declare var ___keepComment: any;
/**
 * Vue component for the {@link wijmo.chart.map.FlexMap} control.
 *
 * The <b>wj-flex-map</b> component may contain
 * the following child components:
 * {@link wijmo.vue2.chart.WjFlexChartLegend}
 * , {@link wijmo.vue2.chart.map.WjScatterMapLayer}
 * , {@link wijmo.vue2.chart.map.WjGeoMapLayer}
 * and {@link wijmo.vue2.chart.map.WjGeoGridLayer}.
 *
 * The component supports all properties and events of the pure JavaScript {@link wijmo.chart.map.FlexMap} control it represents.
 *
 * The component includes an <b>initialized</b> event that is raised when the control is initialized after it is added to the page.
 * You can use this event to perform further initialization in addition to setting properties in markup.
 * The signature of the handler function is the same as any other Wijmo event handlers.
 */
export declare var WjFlexMap: any;
/**
 * Vue component for the {@link wijmo.chart.map.ScatterMapLayer} class.
 *
 * The <b>wj-scatter-map-layer</b> component should be contained in
 * a {@link wijmo.vue2.chart.map.WjFlexMap} component.
 *
 * The <b>wj-scatter-map-layer</b> component may contain
 * a {@link wijmo.vue2.chart.map.WjColorScale} child component.
 *
 * The component supports all properties and events of the pure JavaScript {@link wijmo.chart.map.ScatterMapLayer} class it represents.
 *
 * The component includes an <b>initialized</b> event that is raised when the control is initialized after it is added to the page.
 * You can use this event to perform further initialization in addition to setting properties in markup.
 * The signature of the handler function is the same as any other Wijmo event handlers.
 */
export declare var WjScatterMapLayer: any;
/**
 * Vue component for the {@link wijmo.chart.map.GeoMapLayer} class.
 *
 * The <b>wj-geo-map-layer</b> component should be contained in
 * a {@link wijmo.vue2.chart.map.WjFlexMap} component.
 *
 * The <b>wj-geo-map-layer</b> component may contain
 * a {@link wijmo.vue2.chart.map.WjColorScale} child component.
 *
 * The component supports all properties and events of the pure JavaScript {@link wijmo.chart.map.GeoMapLayer} class it represents.
 *
 * The component includes an <b>initialized</b> event that is raised when the control is initialized after it is added to the page.
 * You can use this event to perform further initialization in addition to setting properties in markup.
 * The signature of the handler function is the same as any other Wijmo event handlers.
 */
export declare var WjGeoMapLayer: any;
/**
 * Vue component for the {@link wijmo.chart.map.GeoGridLayer} class.
 *
 * The <b>wj-geo-grid-layer</b> component should be contained in
 * a {@link wijmo.vue2.chart.map.WjFlexMap} component.
 *
 * The <b>wj-geo-grid-layer</b> component may contain
 * a {@link wijmo.vue2.chart.map.WjColorScale} child component.
 *
 * The component supports all properties and events of the pure JavaScript {@link wijmo.chart.map.GeoGridLayer} class it represents.
 *
 * The component includes an <b>initialized</b> event that is raised when the control is initialized after it is added to the page.
 * You can use this event to perform further initialization in addition to setting properties in markup.
 * The signature of the handler function is the same as any other Wijmo event handlers.
 */
export declare var WjGeoGridLayer: any;
/**
 * Vue component for the {@link wijmo.chart.map.ColorScale} class.
 *
 * The <b>wj-color-scale</b> component should be contained in
 * one of the following components:
 * {@link wijmo.vue2.chart.map.WjScatterMapLayer}
 * , {@link wijmo.vue2.chart.map.WjGeoMapLayer}
 *  or {@link wijmo.vue2.chart.map.WjGeoGridLayer}.
 *
 * The component supports all properties and events of the pure JavaScript {@link wijmo.chart.map.ColorScale} class it represents.
 *
 * The component includes an <b>initialized</b> event that is raised when the control is initialized after it is added to the page.
 * You can use this event to perform further initialization in addition to setting properties in markup.
 * The signature of the handler function is the same as any other Wijmo event handlers.
 */
export declare var WjColorScale: any;
export declare function registerChartMap(app: any): void;
