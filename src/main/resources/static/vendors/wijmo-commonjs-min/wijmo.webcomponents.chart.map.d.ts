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
import { WjComponentBehavior, IPropsMeta } from 'wijmo/wijmo.webcomponents.base';
import * as wjcChartMap from 'wijmo/wijmo.chart.map';
export declare class WjcFlexMap extends wjcChartMap.FlexMap {
    _wjBehaviour: WjComponentBehavior;
    constructor();
    static readonly observedAttributes: IPropsMeta;
    connectedCallback(): void;
    attributeChangedCallback(name: string, oldValue: string, newValue: string): void;
    disconnectedCallback(): void;
    addEventListener(...args: any[]): void;
}
export declare class WjcScatterMapLayer extends HTMLElement {
    static wrappedClass: () => typeof wjcChartMap.ScatterMapLayer;
    static parentProp: string;
    static siblingId: string;
    _wjBehaviour: WjComponentBehavior;
    constructor();
    static readonly observedAttributes: IPropsMeta;
    connectedCallback(): void;
    attributeChangedCallback(name: string, oldValue: string, newValue: string): void;
    disconnectedCallback(): void;
}
export declare class WjcGeoMapLayer extends HTMLElement {
    static wrappedClass: () => typeof wjcChartMap.GeoMapLayer;
    static parentProp: string;
    static siblingId: string;
    _wjBehaviour: WjComponentBehavior;
    constructor();
    static readonly observedAttributes: IPropsMeta;
    connectedCallback(): void;
    attributeChangedCallback(name: string, oldValue: string, newValue: string): void;
    disconnectedCallback(): void;
}
export declare class WjcGeoGridLayer extends HTMLElement {
    static wrappedClass: () => typeof wjcChartMap.GeoGridLayer;
    static parentProp: string;
    static siblingId: string;
    _wjBehaviour: WjComponentBehavior;
    constructor();
    static readonly observedAttributes: IPropsMeta;
    connectedCallback(): void;
    attributeChangedCallback(name: string, oldValue: string, newValue: string): void;
    disconnectedCallback(): void;
}
export declare class WjcColorScale extends HTMLElement {
    static wrappedClass: () => typeof wjcChartMap.ColorScale;
    static parentProp: string;
    _wjBehaviour: WjComponentBehavior;
    constructor();
    static readonly observedAttributes: IPropsMeta;
    connectedCallback(): void;
    attributeChangedCallback(name: string, oldValue: string, newValue: string): void;
    disconnectedCallback(): void;
}
