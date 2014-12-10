/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.camera.one.v2.common;

import android.graphics.PointF;

import com.android.camera.async.Updatable;

/**
 * A ManualAutoFocus implementation which updates metering parameters and runs
 * an AF Scan.
 */
public class ManualAutoFocusImpl implements GenericOneCameraImpl.ManualAutoFocus {
    private final Updatable<MeteringParameters> mMeteringParameters;
    private final Runnable mAFScanRunnable;

    public ManualAutoFocusImpl(Updatable<MeteringParameters> meteringParameters,
            Runnable afScanRunnable) {
        mMeteringParameters = meteringParameters;
        mAFScanRunnable = afScanRunnable;
    }

    @Override
    public void triggerFocusAndMeterAtPoint(float nx, float ny) {
        PointF point = new PointF(nx, ny);
        mMeteringParameters.update(new MeteringParameters(point, point, MeteringParameters.Mode
                .POINT));
        mAFScanRunnable.run();
    }
}
