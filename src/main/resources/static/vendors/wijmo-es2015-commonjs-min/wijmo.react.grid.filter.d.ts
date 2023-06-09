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
 * {@module wijmo.react.grid.filter}
 * Contains React components for the <b>wijmo.grid.filter</b> module.
 */
/**
 *
 */
export declare var ___keepComment: any;
import { ComponentBase } from 'wijmo/wijmo.react.base';
/**
 * React component for the {@link wijmo.grid.filter.FlexGridFilter} class.
 *
 * The <b>flex-grid-filter</b> component should be contained in
 * a {@link wijmo.react.grid.FlexGrid} component.
 *
 * The component supports all properties and events of the pure JavaScript {@link wijmo.grid.filter.FlexGridFilter} class it represents.
 *
 * The component includes an <b>initialized</b> event that is raised when the control is initialized after it is added to the page.
 * You can use this event to perform further initialization in addition to setting properties in JSX.
 * The signature of the handler function is the same as any other Wijmo event handlers.
 */
export declare class FlexGridFilter extends ComponentBase {
    props: {
        template?: any;
        children?: any;
        showFilterIcons?: any;
        showSortButtons?: any;
        defaultFilterType?: any;
        filterColumns?: any;
        initialized?: any;
        editingFilter?: any;
        filterChanging?: any;
        filterChanged?: any;
        filterApplied?: any;
        [key: string]: any;
    };
    _parentInCtor: boolean;
    constructor(props: any);
}
