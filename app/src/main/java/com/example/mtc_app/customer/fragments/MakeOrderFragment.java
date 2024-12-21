package com.example.mtc_app.customer.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mtc_app.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeOrderFragment extends Fragment {

    private CheckBox cbPowerBlock, cbHardenedConcrete, cbBrick, cbSoil, cbSteelBar;
    private CheckBox cbAggregateFine, cbAggregateCoarse, cbCement, cbNDT, cbBitumen;

    // Aggregate Fine
    private CheckBox cbSieveAnalysisFine, cbWaterAbsorptionFine, cbSpecificGravityFine;

    //Aggregate Coarse
    private CheckBox cbWaterAbsorptionCoarse, cbCrushingValueCoarse, cbFlakinessIndexCoarse, cbAbrasionValueCoarse, cbElongationIndexCoarse,  cbImpactValueCoarse, cbSieveAnalysisCoarse, cbSpecificGravityCoarse;

    //Paver Block
    private CheckBox cbCompressiveStrengthPaver, cbWaterAbsorptionPaver;

    //Cement and last Concrete
    private CheckBox cbSoundnessCement, cbFinalSettingTimeCement, cbInitialSettingTimeCement, cbConsistencyCement, cbCompressiveCement, cbFinenessCement, cbCompressiveStrengthConcrete;

    //Brick
    private CheckBox cbWaterAbsorptionBrick,cbDimensionTestBrick, cbCompressiveStrengthBrick;

    //Soil
    private CheckBox cbSpecificGravitySoil, cbGrainSizeAnalysisSoil, cbLiquidLimitSoil, cbPlasticLimitSoil,
            cbLightCompactionTestSoil, cbHeavyCompactionTestSoil, cbFreeSwellIndexSoil, cbCaliforniaBearingRatioSoil,
            cbDirectShearSoil, cbFieldDensitySoil, cbUCSSoil, cbTriaxialSoil, cbConsolidationSoil;

    //Steel Reinforcement Bar & NDT
    private CheckBox cbUltimateTensileStrengthBar, cbYieldStrengthBar, cbElongationBar, cbBendTestBar,
            cbReBendTestBar, cbWeightMeterBar,cbUltrasonicPulseVelocityNDT, cbReboundHammerTestNDT;

    // Bitumen
    private CheckBox cbAbsoluteViscosityBitumen, cbKinematicViscosityBitumen, cbPenetrationValueBitumen,
            cbSofteningPointBitumen, cbDuctilityBitumen, cbSpecificGravityBitumen, cbLossOnHeatingBitumen;
    private TextInputLayout tilPowerBlockQuantity, tilHardenedConcreteQuantity, tilBrickQuantity, tilSoilQuantity, tilSteelBarQuantity;
    private TextInputLayout tilAggregateFineQuantity, tilAggregateCoarseQuantity, tilCementQuantity, tilBitumenQuantity, tilNDTQuantity;


    // Prices for each item
    private final Map<CheckBox, Integer> priceMap = new HashMap<>();
    private TextView tvTotalPrice;
    private int totalPrice = 0;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_make_order_fragment, container, false);

        // Initialize CheckBoxes
        cbPowerBlock = view.findViewById(R.id.cb_power_block);
        cbHardenedConcrete = view.findViewById(R.id.cb_hardened_concrete);
        cbBrick = view.findViewById(R.id.cb_brick);
        cbSoil = view.findViewById(R.id.cb_soil);
        cbSteelBar = view.findViewById(R.id.cb_steel_bar);
        cbAggregateFine = view.findViewById(R.id.cb_aggregate_fine);
        cbAggregateCoarse = view.findViewById(R.id.cb_aggregate_coarse);
        cbCement = view.findViewById(R.id.cb_cement);
        cbBitumen = view.findViewById(R.id.cb_bitumen);
        cbNDT = view.findViewById(R.id.cb_ndt);

        // Initialize Aggregate-Fine CheckBoxes
        cbSieveAnalysisFine = view.findViewById(R.id.AGGREGATE_Fine_sieve_analysis);
        cbWaterAbsorptionFine = view.findViewById(R.id.AGGREGATE_Fine_water_absorption);
        cbSpecificGravityFine = view.findViewById(R.id.AGGREGATE_Fine_specific_gravity);

        // Initialize AGGREGATE (COARSE) related checkboxes
        cbSieveAnalysisCoarse = view.findViewById(R.id.AGGREGATE_Coarse_sieve_analysis);
        cbImpactValueCoarse = view.findViewById(R.id.AGGREGATE_Coarse_impact_value);
        cbCrushingValueCoarse = view.findViewById(R.id.AGGREGATE_Coarse_crushing_value);
        cbWaterAbsorptionCoarse = view.findViewById(R.id.AGGREGATE_Coarse_water_absorption);
        cbSpecificGravityCoarse = view.findViewById(R.id.AGGREGATE_Coarse_specific_gravity);
        cbFlakinessIndexCoarse = view.findViewById(R.id.AGGREGATE_Coarse_flakiness_index);
        cbElongationIndexCoarse = view.findViewById(R.id.AGGREGATE_Coarse_elongation_index);
        cbAbrasionValueCoarse = view.findViewById(R.id.AGGREGATE_Coarse_abrasion_value);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);

        // Initialize related checkboxes for PAVER category
        cbCompressiveStrengthPaver = view.findViewById(R.id.PAVER_compressive_strength);
        cbWaterAbsorptionPaver = view.findViewById(R.id.PAVER_water_absorption);

        // Initialize TextInputLayouts for Quantity
        tilPowerBlockQuantity = view.findViewById(R.id.til_power_block_quantity);
        tilHardenedConcreteQuantity = view.findViewById(R.id.til_hardened_concrete_quantity);
        tilBrickQuantity = view.findViewById(R.id.til_brick_quantity);
        tilSoilQuantity = view.findViewById(R.id.til_soil_quantity);
        tilSteelBarQuantity = view.findViewById(R.id.til_steel_bar_quantity);
        tilAggregateFineQuantity = view.findViewById(R.id.til_aggregate_fine_quantity);
        tilAggregateCoarseQuantity = view.findViewById(R.id.til_aggregate_coarse_quantity);
        tilCementQuantity = view.findViewById(R.id.til_cement_quantity);
        tilBitumenQuantity = view.findViewById(R.id.til_bitumen_quantity);
        tilNDTQuantity = view.findViewById(R.id.til_ndt_quantity);

        // Initialize Cement checkbox
        cbConsistencyCement = view.findViewById(R.id.CEMENT_consistency);
        cbInitialSettingTimeCement = view.findViewById(R.id.CEMENT_initial_setting_time);
        cbFinalSettingTimeCement = view.findViewById(R.id.CEMENT_final_setting_time);
        cbSoundnessCement = view.findViewById(R.id.CEMENT_soundness);
        cbFinenessCement = view.findViewById(R.id.CEMENT_fineness);
        cbCompressiveCement = view.findViewById(R.id.CEMENT_compressive);

        // Initialize Concrete checkbox
        cbCompressiveStrengthConcrete = view.findViewById(R.id.CONCRETE_compressive_strength);

        // Initialize Brick checkbox
        cbCompressiveStrengthBrick = view.findViewById(R.id.BRICK_compressive_strength);
        cbDimensionTestBrick = view.findViewById(R.id.BRICK_dimension_test);
        cbWaterAbsorptionBrick = view.findViewById(R.id.BRICK_Fine_water_absorption);

        // Initialize Soil checkbox and its related checkboxes
        cbSpecificGravitySoil = view.findViewById(R.id.SOIL_specific_gravity);
        cbGrainSizeAnalysisSoil = view.findViewById(R.id.SOIL_grain_size_analysis);
        cbLiquidLimitSoil = view.findViewById(R.id.SOIL_liquid_limit);
        cbPlasticLimitSoil = view.findViewById(R.id.SOIL_plastic_limit);
        cbLightCompactionTestSoil = view.findViewById(R.id.SOIL_light_compaction_test);
        cbHeavyCompactionTestSoil = view.findViewById(R.id.SOIL_heavy_compaction_test);
        cbFreeSwellIndexSoil = view.findViewById(R.id.SOIL_free_swell_index);
        cbCaliforniaBearingRatioSoil = view.findViewById(R.id.SOIL_california_bearing_ratio);
        cbDirectShearSoil = view.findViewById(R.id.SOIL_direct_shear);
        cbFieldDensitySoil = view.findViewById(R.id.SOIL_field_density);
        cbUCSSoil = view.findViewById(R.id.SOIL_ucs);
        cbTriaxialSoil = view.findViewById(R.id.SOIL_triaxial);
        cbConsolidationSoil = view.findViewById(R.id.SOIL_consolidation);

        // Initialize Bar checkboxes
        cbUltimateTensileStrengthBar = view.findViewById(R.id.BAR_Ultimate_tensile_strength);
        cbYieldStrengthBar = view.findViewById(R.id.BAR_yield_strength);
        cbElongationBar = view.findViewById(R.id.BAR_elongation);
        cbBendTestBar = view.findViewById(R.id.BAR_bend_test);
        cbReBendTestBar = view.findViewById(R.id.BAR_re_bend_test);
        cbWeightMeterBar = view.findViewById(R.id.BAR_weight_meter);

        // Bitumen checkboxes
        cbAbsoluteViscosityBitumen = view.findViewById(R.id.BITUMEN_absolute_viscosity);
        cbKinematicViscosityBitumen = view.findViewById(R.id.BITUMEN_Kinematic_viscosity);
        cbPenetrationValueBitumen = view.findViewById(R.id.BITUMEN_penetration_value);
        cbSofteningPointBitumen = view.findViewById(R.id.BITUMEN_softening_point);
        cbDuctilityBitumen = view.findViewById(R.id.BITUMEN_ductility);
        cbSpecificGravityBitumen = view.findViewById(R.id.BITUMEN_specific_gravity);
        cbLossOnHeatingBitumen = view.findViewById(R.id.BITUMEN_Loss_on_heating);

        // Initialize NDT checkboxes
        cbUltrasonicPulseVelocityNDT = view.findViewById(R.id.NDT_ultrasonic_pulse_velocity);
        cbReboundHammerTestNDT = view.findViewById(R.id.NDT_rebound_hammer_test);

        // Set up listeners for checkboxes
        setUpCheckboxListener(cbPowerBlock, tilPowerBlockQuantity);
        setUpCheckboxListener(cbHardenedConcrete, tilHardenedConcreteQuantity);
        setUpCheckboxListener(cbBrick, tilBrickQuantity);
        setUpCheckboxListener(cbSoil, tilSoilQuantity);
        setUpCheckboxListener(cbSteelBar, tilSteelBarQuantity);
        setUpCheckboxListener(cbAggregateCoarse, tilAggregateCoarseQuantity);
        setUpCheckboxListener(cbCement, tilCementQuantity);
        setUpCheckboxListener(cbNDT, tilNDTQuantity);

        //price Aggregate Fine listeners
        setupPriceChangeListener(cbSpecificGravityFine, 250);
        setupPriceChangeListener(cbWaterAbsorptionFine, 350);
        setupPriceChangeListener(cbSieveAnalysisFine, 350);
        setupPriceChangeListener(cbElongationIndexCoarse, 350);

        //price Aggregate Coarse listeners
        setupPriceChangeListener(cbSieveAnalysisCoarse, 250);
        setupPriceChangeListener(cbImpactValueCoarse, 250);
        setupPriceChangeListener(cbCrushingValueCoarse, 350);
        setupPriceChangeListener(cbFlakinessIndexCoarse, 350);
        setupPriceChangeListener(cbElongationIndexCoarse, 350);
        setupPriceChangeListener(cbAbrasionValueCoarse, 350);
        setupPriceChangeListener(cbWaterAbsorptionCoarse, 350);
        setupPriceChangeListener(cbSpecificGravityCoarse, 350);

        //price Power Block listeners
        setupPriceChangeListener(cbWaterAbsorptionPaver, 250);
        setupPriceChangeListener(cbCompressiveStrengthPaver, 350);

        //price Cement listeners
        setupPriceChangeListener(cbConsistencyCement, 250);
        setupPriceChangeListener(cbInitialSettingTimeCement, 350);
        setupPriceChangeListener(cbFinalSettingTimeCement, 350);
        setupPriceChangeListener(cbSoundnessCement, 350);
        setupPriceChangeListener(cbFinenessCement, 350);
        setupPriceChangeListener(cbCompressiveCement, 350);

        //price Hardened Concrete listeners
        setupPriceChangeListener(cbCompressiveStrengthConcrete, 250);

        //price Brick listeners
        setupPriceChangeListener(cbCompressiveStrengthBrick, 350);
        setupPriceChangeListener(cbDimensionTestBrick, 350);
        setupPriceChangeListener(cbWaterAbsorptionBrick, 350);

        //price Soil listeners
        setupPriceChangeListener(cbSpecificGravitySoil, 250);
        setupPriceChangeListener(cbGrainSizeAnalysisSoil, 350);
        setupPriceChangeListener(cbLiquidLimitSoil, 350);
        setupPriceChangeListener(cbPlasticLimitSoil, 350);
        setupPriceChangeListener(cbLightCompactionTestSoil, 350);
        setupPriceChangeListener(cbHeavyCompactionTestSoil, 350);
        setupPriceChangeListener(cbFreeSwellIndexSoil, 250);
        setupPriceChangeListener(cbCaliforniaBearingRatioSoil, 350);
        setupPriceChangeListener(cbDirectShearSoil, 350);
        setupPriceChangeListener(cbFieldDensitySoil, 350);
        setupPriceChangeListener(cbUCSSoil, 350);
        setupPriceChangeListener(cbTriaxialSoil, 350);
        setupPriceChangeListener(cbConsolidationSoil, 350);

        //price Steel Bar listeners
        setupPriceChangeListener(cbUltimateTensileStrengthBar, 250);
        setupPriceChangeListener(cbYieldStrengthBar, 350);
        setupPriceChangeListener(cbElongationBar, 350);
        setupPriceChangeListener(cbBendTestBar, 350);
        setupPriceChangeListener(cbReBendTestBar, 350);
        setupPriceChangeListener(cbWeightMeterBar, 350);

        //price Bitumen listeners
        setupPriceChangeListener(cbAbsoluteViscosityBitumen, 250);
        setupPriceChangeListener(cbKinematicViscosityBitumen, 350);
        setupPriceChangeListener(cbPenetrationValueBitumen, 350);
        setupPriceChangeListener(cbSofteningPointBitumen, 350);
        setupPriceChangeListener(cbDuctilityBitumen, 350);
        setupPriceChangeListener(cbSpecificGravityBitumen, 350);
        setupPriceChangeListener(cbLossOnHeatingBitumen, 350);

        //price NDT listeners
        setupPriceChangeListener(cbUltrasonicPulseVelocityNDT, 250);
        setupPriceChangeListener(cbReboundHammerTestNDT, 350);


        // Special setup for Aggregate Fine checkbox
        cbAggregateFine.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show the Aggregate Fine quantity input and other checkboxes
                tilAggregateFineQuantity.setVisibility(View.VISIBLE);
                cbSieveAnalysisFine.setVisibility(View.VISIBLE);
                cbWaterAbsorptionFine.setVisibility(View.VISIBLE);
                cbSpecificGravityFine.setVisibility(View.VISIBLE);
            } else {
                // Hide the Aggregate Fine quantity input and other checkboxes
                tilAggregateFineQuantity.setVisibility(View.GONE);
                cbSieveAnalysisFine.setVisibility(View.GONE);
                cbWaterAbsorptionFine.setVisibility(View.GONE);
                cbSpecificGravityFine.setVisibility(View.GONE);

                // Uncheck the additional checkboxes when hiding them
                cbSieveAnalysisFine.setChecked(false);
                cbWaterAbsorptionFine.setChecked(false);
                cbSpecificGravityFine.setChecked(false);
            }
        });

        // Special setup for Aggregate Coarse checkbox
        cbAggregateCoarse.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all the related checkboxes
                tilAggregateCoarseQuantity.setVisibility(View.VISIBLE);
                cbSieveAnalysisCoarse.setVisibility(View.VISIBLE);
                cbImpactValueCoarse.setVisibility(View.VISIBLE);
                cbCrushingValueCoarse.setVisibility(View.VISIBLE);
                cbWaterAbsorptionCoarse.setVisibility(View.VISIBLE);
                cbSpecificGravityCoarse.setVisibility(View.VISIBLE);
                cbFlakinessIndexCoarse.setVisibility(View.VISIBLE);
                cbElongationIndexCoarse.setVisibility(View.VISIBLE);
                cbAbrasionValueCoarse.setVisibility(View.VISIBLE);
            } else {
                // Hide all the related checkboxes and uncheck them
                tilAggregateCoarseQuantity.setVisibility(View.GONE);
                cbSieveAnalysisCoarse.setVisibility(View.GONE);
                cbImpactValueCoarse.setVisibility(View.GONE);
                cbCrushingValueCoarse.setVisibility(View.GONE);
                cbWaterAbsorptionCoarse.setVisibility(View.GONE);
                cbSpecificGravityCoarse.setVisibility(View.GONE);
                cbFlakinessIndexCoarse.setVisibility(View.GONE);
                cbElongationIndexCoarse.setVisibility(View.GONE);
                cbAbrasionValueCoarse.setVisibility(View.GONE);

                cbSieveAnalysisCoarse.setChecked(false);
                cbImpactValueCoarse.setChecked(false);
                cbCrushingValueCoarse.setChecked(false);
                cbWaterAbsorptionCoarse.setChecked(false);
                cbSpecificGravityCoarse.setChecked(false);
                cbFlakinessIndexCoarse.setChecked(false);
                cbElongationIndexCoarse.setChecked(false);
                cbAbrasionValueCoarse.setChecked(false);
            }
        });


        // Set up listener for PAVER checkbox
        cbPowerBlock.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show the related checkboxes
                tilPowerBlockQuantity.setVisibility(View.VISIBLE);
                cbCompressiveStrengthPaver.setVisibility(View.VISIBLE);
                cbWaterAbsorptionPaver.setVisibility(View.VISIBLE);
            } else {
                // Hide the related checkboxes and uncheck them
                tilPowerBlockQuantity.setVisibility(View.GONE);
                cbCompressiveStrengthPaver.setVisibility(View.GONE);
                cbWaterAbsorptionPaver.setVisibility(View.GONE);

                cbCompressiveStrengthPaver.setChecked(false);
                cbWaterAbsorptionPaver.setChecked(false);
            }
        });

        // Set up listener for the Cement checkbox
        cbCement.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all related checkboxes when cbCement is checked
                tilCementQuantity.setVisibility(View.VISIBLE);
                cbConsistencyCement.setVisibility(View.VISIBLE);
                cbInitialSettingTimeCement.setVisibility(View.VISIBLE);
                cbFinalSettingTimeCement.setVisibility(View.VISIBLE);
                cbSoundnessCement.setVisibility(View.VISIBLE);
                cbFinenessCement.setVisibility(View.VISIBLE);
                cbCompressiveCement.setVisibility(View.VISIBLE);
            } else {
                // Hide all related checkboxes when cbCement is unchecked
                tilCementQuantity.setVisibility(View.GONE);
                cbConsistencyCement.setVisibility(View.GONE);
                cbInitialSettingTimeCement.setVisibility(View.GONE);
                cbFinalSettingTimeCement.setVisibility(View.GONE);
                cbSoundnessCement.setVisibility(View.GONE);
                cbFinenessCement.setVisibility(View.GONE);
                cbCompressiveCement.setVisibility(View.GONE);

                // Uncheck them as well
                cbConsistencyCement.setChecked(false);
                cbInitialSettingTimeCement.setChecked(false);
                cbFinalSettingTimeCement.setChecked(false);
                cbSoundnessCement.setChecked(false);
                cbFinenessCement.setChecked(false);
                cbCompressiveCement.setChecked(false);
            }
        });

        cbHardenedConcrete.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show the Compressive Strength checkbox when cbConcrete is checked
                tilHardenedConcreteQuantity.setVisibility(View.VISIBLE);
                cbCompressiveStrengthConcrete.setVisibility(View.VISIBLE);
            } else {
                // Hide the Compressive Strength checkbox when cbConcrete is unchecked
                tilHardenedConcreteQuantity.setVisibility(View.GONE);
                cbCompressiveStrengthConcrete.setVisibility(View.GONE);

                // Uncheck it as well
                cbCompressiveStrengthConcrete.setChecked(false);
            }
        });

        // Set up listener for the Brick checkbox
        cbBrick.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Brick-related checkboxes when cbBrick is checked
                tilBrickQuantity.setVisibility(View.VISIBLE);
                cbCompressiveStrengthBrick.setVisibility(View.VISIBLE);
                cbDimensionTestBrick.setVisibility(View.VISIBLE);
                cbWaterAbsorptionBrick.setVisibility(View.VISIBLE);
            } else {
                // Hide all Brick-related checkboxes when cbBrick is unchecked
                tilBrickQuantity.setVisibility(View.GONE);
                cbCompressiveStrengthBrick.setVisibility(View.GONE);
                cbDimensionTestBrick.setVisibility(View.GONE);
                cbWaterAbsorptionBrick.setVisibility(View.GONE);

                // Uncheck all related checkboxes
                cbCompressiveStrengthBrick.setChecked(false);
                cbDimensionTestBrick.setChecked(false);
                cbWaterAbsorptionBrick.setChecked(false);
            }
        });

        // Set up listener for the Soil checkbox
        cbSoil.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Soil-related checkboxes when cbSoil is checked
                tilSoilQuantity.setVisibility(View.VISIBLE);
                cbSpecificGravitySoil.setVisibility(View.VISIBLE);
                cbGrainSizeAnalysisSoil.setVisibility(View.VISIBLE);
                cbLiquidLimitSoil.setVisibility(View.VISIBLE);
                cbPlasticLimitSoil.setVisibility(View.VISIBLE);
                cbLightCompactionTestSoil.setVisibility(View.VISIBLE);
                cbHeavyCompactionTestSoil.setVisibility(View.VISIBLE);
                cbFreeSwellIndexSoil.setVisibility(View.VISIBLE);
                cbCaliforniaBearingRatioSoil.setVisibility(View.VISIBLE);
                cbDirectShearSoil.setVisibility(View.VISIBLE);
                cbFieldDensitySoil.setVisibility(View.VISIBLE);
                cbUCSSoil.setVisibility(View.VISIBLE);
                cbTriaxialSoil.setVisibility(View.VISIBLE);
                cbConsolidationSoil.setVisibility(View.VISIBLE);
            } else {
                // Hide all Soil-related checkboxes when cbSoil is unchecked
                tilSoilQuantity.setVisibility(View.GONE);
                cbSpecificGravitySoil.setVisibility(View.GONE);
                cbGrainSizeAnalysisSoil.setVisibility(View.GONE);
                cbLiquidLimitSoil.setVisibility(View.GONE);
                cbPlasticLimitSoil.setVisibility(View.GONE);
                cbLightCompactionTestSoil.setVisibility(View.GONE);
                cbHeavyCompactionTestSoil.setVisibility(View.GONE);
                cbFreeSwellIndexSoil.setVisibility(View.GONE);
                cbCaliforniaBearingRatioSoil.setVisibility(View.GONE);
                cbDirectShearSoil.setVisibility(View.GONE);
                cbFieldDensitySoil.setVisibility(View.GONE);
                cbUCSSoil.setVisibility(View.GONE);
                cbTriaxialSoil.setVisibility(View.GONE);
                cbConsolidationSoil.setVisibility(View.GONE);
            }
        });

        // Set up listeners for the Bar checkboxes if needed
        cbSteelBar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Bar-related checkboxes when Ultimate Tensile Strength is checked
                tilSteelBarQuantity.setVisibility(View.VISIBLE);
                cbUltimateTensileStrengthBar.setVisibility(View.VISIBLE);
                cbYieldStrengthBar.setVisibility(View.VISIBLE);
                cbElongationBar.setVisibility(View.VISIBLE);
                cbBendTestBar.setVisibility(View.VISIBLE);
                cbReBendTestBar.setVisibility(View.VISIBLE);
                cbWeightMeterBar.setVisibility(View.VISIBLE);
            } else {
                // Hide all Bar-related checkboxes when it's unchecked
                tilSteelBarQuantity.setVisibility(View.GONE);
                cbUltimateTensileStrengthBar.setVisibility(View.GONE);
                cbYieldStrengthBar.setVisibility(View.GONE);
                cbElongationBar.setVisibility(View.GONE);
                cbBendTestBar.setVisibility(View.GONE);
                cbReBendTestBar.setVisibility(View.GONE);
                cbWeightMeterBar.setVisibility(View.GONE);
            }
        });

        // Set up listener for Bitumen checkbox
        cbBitumen.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Bitumen-related checkboxes and quantity input when cbBitumen is checked
                tilBitumenQuantity.setVisibility(View.VISIBLE);
                cbAbsoluteViscosityBitumen.setVisibility(View.VISIBLE);
                cbKinematicViscosityBitumen.setVisibility(View.VISIBLE);
                cbPenetrationValueBitumen.setVisibility(View.VISIBLE);
                cbSofteningPointBitumen.setVisibility(View.VISIBLE);
                cbDuctilityBitumen.setVisibility(View.VISIBLE);
                cbSpecificGravityBitumen.setVisibility(View.VISIBLE);
                cbLossOnHeatingBitumen.setVisibility(View.VISIBLE);
            } else {
                // Hide all Bitumen-related checkboxes and quantity input when cbBitumen is unchecked
                tilBitumenQuantity.setVisibility(View.GONE);
                cbAbsoluteViscosityBitumen.setVisibility(View.GONE);
                cbKinematicViscosityBitumen.setVisibility(View.GONE);
                cbPenetrationValueBitumen.setVisibility(View.GONE);
                cbSofteningPointBitumen.setVisibility(View.GONE);
                cbDuctilityBitumen.setVisibility(View.GONE);
                cbSpecificGravityBitumen.setVisibility(View.GONE);
                cbLossOnHeatingBitumen.setVisibility(View.GONE);
            }
            calculateTotalPrice();
        });


        // Set up listener for NDT checkbox
        cbNDT.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tilNDTQuantity.setVisibility(View.VISIBLE);
                cbUltrasonicPulseVelocityNDT.setVisibility(View.VISIBLE);
                cbReboundHammerTestNDT.setVisibility(View.VISIBLE);
            } else {
                tilNDTQuantity.setVisibility(View.GONE);
                cbUltrasonicPulseVelocityNDT.setVisibility(View.GONE);
                cbReboundHammerTestNDT.setVisibility(View.GONE);
            }
        });


        return view;
    }

    private void setupPriceChangeListener(CheckBox checkBox, int price) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            totalPrice += isChecked ? price : -price;
            calculateTotalPrice();
        });
    }


    private void calculateTotalPrice() {
        tvTotalPrice.setText("Total Price: " + totalPrice + " /- Rs");
    }



    // Checkbox Listener to show/hide TextInputLayout
    private void setUpCheckboxListener(CheckBox checkBox, TextInputLayout textInputLayout) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                textInputLayout.setVisibility(View.VISIBLE);
            } else {
                textInputLayout.setVisibility(View.GONE);
            }
        });
    }
}
