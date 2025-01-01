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

    private CheckBox cbPowerBlock, cbSteel, cbBrick, cbSoil, cbAacBlock;
    private CheckBox cbAggregateFine, cbAggregateCoarse, cbCement, cbNDT, cbBitumen;

    // Aggregate Fine
    private CheckBox finenessModulusBygradationFine, siltContentFine, specificGravityAndWaterAbsorptionFine, soundnessFine, alkaliReactivityFine;

    //Aggregate Coarse
    private CheckBox cbImpactValueCoarse, SpecificGravityAndWaterAbsorptionCoarse, cbCrushingValueCoarse, cbSoundnessCyclesCoarse,  cbFlakinessIndexAndElongationIndexCoarse, cbGradingOfAggregateCoarse, cbAbrasionValueCoarse, cbAlkaliReactivityCoarse;

    //Paver Block
    private CheckBox cbCompressiveStrengthPaver, cbWaterAbsorptionPaver;

    //Cement and last Concrete
    private CheckBox cbFinessByBlainCement, cbInitialSettingTimeCement, cbConsistencyCement, cbCompressiveCement, cbFinenessCement,cbSoundenessCemenet, cbCementCompressiveStrengthMortar, cbCementChemicalAnalysis, cbCompressiveStrengthConcrete;

    //Brick
    private CheckBox cbWaterAbsorptionBrick,cbDimensionTestBrick, cbCompressiveStrengthBrick, cbBrickEfflorescence;

    //Soil
    private CheckBox cbSoilCBRTestUnsoaked, cbGrainSizeAnalysisSoil, cbTestSoaked, cbPlasticLimitSoil,
            cbLightCompactionTestSoil, cbHeavyCompactionTestSoil, cbFreeSwellIndexSoil, cbUnconfinedCompressionSoil, cbTriaxialTestUUSoil, cbTriaxialTestCUSoil,  cbSwellingPressureSoil, cbSpecificGravitySoil, cbShrinkageLimitSoil,
            cbDirectShearSoil, cbPermeabilityTestSoil, cbRelativeDensitySoil, cbFieldDensityAndMoistureContentSoil, cbConsolidationSoil;

    //Steel Reinforcement Bar & NDT
    private CheckBox cbMeasurementOfDimensionsAac, cbCompressiveStrengthAac, cbBlocksDensityAac, cbWaterAbsorptionAac,
            cbDryingShrinkageAac, cbMoistureMovementAac,cbUltrasonicPulseVelocityNDT, cbReboundHammerTestNDT;

    // Bitumen
    private CheckBox cbAbsoluteViscosityBitumen, cbKinematicViscosityBitumen, cbPenetrationValueBitumen,
            cbSofteningPointBitumen, cbDuctilityBitumen, cbSpecificGravityBitumen, cbLossOnHeatingBitumen;
    private TextInputLayout tilPowerBlockQuantity, tilSteelQuantity, tilBrickQuantity, tilSoilQuantity, tilAacBlockQuantity;
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
        cbSteel = view.findViewById(R.id.cb_steel);
        cbBrick = view.findViewById(R.id.cb_brick);
        cbSoil = view.findViewById(R.id.cb_soil);
        cbAacBlock = view.findViewById(R.id.cb_aac_block);
        cbAggregateFine = view.findViewById(R.id.cb_aggregate_fine);
        cbAggregateCoarse = view.findViewById(R.id.cb_aggregate_coarse);
        cbCement = view.findViewById(R.id.cb_cement);
        cbBitumen = view.findViewById(R.id.cb_bitumen);
        cbNDT = view.findViewById(R.id.cb_ndt);

        // Initialize Aggregate-Fine CheckBoxes
        finenessModulusBygradationFine = view.findViewById(R.id.AGGREGATE_fineness_modulus_bygradation);
        siltContentFine = view.findViewById(R.id.AGGREGATE_silt_content);
        specificGravityAndWaterAbsorptionFine = view.findViewById(R.id.AGGREGATE_specific_gravity_and_water_absorption);
        soundnessFine = view.findViewById(R.id.AGGREGATE_soundness);
        alkaliReactivityFine = view.findViewById(R.id.AGGREGATE_alkali_reactivity);
        cbSoundenessCemenet = view.findViewById(R.id.CEMENT_soundness);

        // Initialize AGGREGATE (COARSE) related checkboxes
        cbGradingOfAggregateCoarse = view.findViewById(R.id.AGGREGATE_grading_of_aggregate);
        cbFlakinessIndexAndElongationIndexCoarse = view.findViewById(R.id.AGGREGATE_flakiness_index_and_elongation_index);
        SpecificGravityAndWaterAbsorptionCoarse = view.findViewById(R.id.AGGREGATE_specific_gravity_and_water_absorption_coarse);
        cbImpactValueCoarse = view.findViewById(R.id.AGGREGATE_impact_value_coarse);
        cbAbrasionValueCoarse = view.findViewById(R.id.AGGREGATE_abrasion_value_coarse);
        cbCrushingValueCoarse = view.findViewById(R.id.AGGREGATE_crushing_value_coarse);
        cbSoundnessCyclesCoarse = view.findViewById(R.id.AGGREGATE_soundness_cycles_coarse);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        cbAlkaliReactivityCoarse = view.findViewById(R.id.AGGREGATE_alkali_reactivity_coarse);

        // Initialize related checkboxes for PAVER category
        cbCompressiveStrengthPaver = view.findViewById(R.id.PAVER_compressive_strength);
        cbWaterAbsorptionPaver = view.findViewById(R.id.PAVER_water_absorption);

        // Initialize TextInputLayouts for Quantity
        tilPowerBlockQuantity = view.findViewById(R.id.til_power_block_quantity);
        tilSteelQuantity = view.findViewById(R.id.til_steel_quantity);
        tilBrickQuantity = view.findViewById(R.id.til_brick_quantity);
        tilSoilQuantity = view.findViewById(R.id.til_soil_quantity);
        tilAacBlockQuantity = view.findViewById(R.id.til_aac_block_quantity);
        tilAggregateFineQuantity = view.findViewById(R.id.til_aggregate_fine_quantity);
        tilAggregateCoarseQuantity = view.findViewById(R.id.til_aggregate_coarse_quantity);
        tilCementQuantity = view.findViewById(R.id.til_cement_quantity);
        tilBitumenQuantity = view.findViewById(R.id.til_bitumen_quantity);
        tilNDTQuantity = view.findViewById(R.id.til_ndt_quantity);

        // Initialize Cement checkbox
        cbConsistencyCement = view.findViewById(R.id.CEMENT_consistency);
        cbInitialSettingTimeCement = view.findViewById(R.id.CEMENT_initial_setting_time);
        cbFinessByBlainCement = view.findViewById(R.id.CEMENT_finess_by_blain);
        cbFinenessCement = view.findViewById(R.id.CEMENT_fineness);
        cbCompressiveCement = view.findViewById(R.id.CEMENT_compressive);
        cbSoundenessCemenet = view.findViewById(R.id.CEMENT_soundness);
        cbCementCompressiveStrengthMortar = view.findViewById(R.id.CEMENT_COMPRESSIVE_STRENGTH_MORTAR);
        cbCementChemicalAnalysis = view.findViewById(R.id.cement_chemical_analysis);

        // Initialize Concrete checkbox
        cbCompressiveStrengthConcrete = view.findViewById(R.id.CONCRETE_compressive_strength);

        // Initialize Brick checkbox
        cbCompressiveStrengthBrick = view.findViewById(R.id.BRICK_compressive_strength);
        cbDimensionTestBrick = view.findViewById(R.id.BRICK_dimension_test);
        cbWaterAbsorptionBrick = view.findViewById(R.id.BRICK_Fine_water_absorption);
        cbBrickEfflorescence = view.findViewById(R.id.BRICK_efflorescence);

        // Initialize Soil checkbox and its related checkboxes
        cbSoilCBRTestUnsoaked = view.findViewById(R.id.SOIL_cbr_test_unsoaked);
        cbGrainSizeAnalysisSoil = view.findViewById(R.id.SOIL_grain_size_analysis);
        cbTestSoaked = view.findViewById(R.id.SOIL_cbr_test_soaked);
        cbPlasticLimitSoil = view.findViewById(R.id.SOIL_plastic_limit);
        cbLightCompactionTestSoil = view.findViewById(R.id.SOIL_light_compaction_test);
        cbHeavyCompactionTestSoil = view.findViewById(R.id.SOIL_heavy_compaction_test);
        cbFreeSwellIndexSoil = view.findViewById(R.id.SOIL_free_swell_index);
        cbShrinkageLimitSoil = view.findViewById(R.id.SOIL_shrinkage_limit);
        cbDirectShearSoil = view.findViewById(R.id.SOIL_direct_shear);
        cbPermeabilityTestSoil = view.findViewById(R.id.SOIL_permeability_test);
        cbRelativeDensitySoil = view.findViewById(R.id.SOIL_relative_density);
        cbFieldDensityAndMoistureContentSoil = view.findViewById(R.id.SOIL_field_density_and_moisture_content);
        cbConsolidationSoil = view.findViewById(R.id.SOIL_consolidation);
        cbUnconfinedCompressionSoil = view.findViewById(R.id.SOIL_unconfined_compression);
        cbTriaxialTestUUSoil = view.findViewById(R.id.SOIL_triaxial_test_uu);
        cbTriaxialTestCUSoil = view.findViewById(R.id.SOIL_triaxial_test_cu);
        cbSwellingPressureSoil = view.findViewById(R.id.SOIL_swelling_pressure);
        cbSpecificGravitySoil = view.findViewById(R.id.SOIL_specific_gravity);

        cbConsolidationSoil = view.findViewById(R.id.SOIL_consolidation);
        cbUnconfinedCompressionSoil = view.findViewById(R.id.SOIL_unconfined_compression);


        // Initialize Bar checkboxes
        cbMeasurementOfDimensionsAac = view.findViewById(R.id.AAC_measurement_of_dimensions);
        cbCompressiveStrengthAac = view.findViewById(R.id.AAC_compressive_strength);
        cbBlocksDensityAac = view.findViewById(R.id.AAC_blocks_density);
        cbWaterAbsorptionAac = view.findViewById(R.id.AAC_water_absorption);
        cbDryingShrinkageAac = view.findViewById(R.id.AAC_drying_shrinkage);
        cbMoistureMovementAac = view.findViewById(R.id.AAC_moisture_movement);

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
        setUpCheckboxListener(cbSteel, tilSteelQuantity);
        setUpCheckboxListener(cbBrick, tilBrickQuantity);
        setUpCheckboxListener(cbSoil, tilSoilQuantity);
        setUpCheckboxListener(cbAacBlock, tilAacBlockQuantity);
        setUpCheckboxListener(cbAggregateCoarse, tilAggregateCoarseQuantity);
        setUpCheckboxListener(cbCement, tilCementQuantity);
        setUpCheckboxListener(cbNDT, tilNDTQuantity);

        //price Aggregate Fine listeners
        setupPriceChangeListener(finenessModulusBygradationFine, 350);
        setupPriceChangeListener(siltContentFine, 350);
        setupPriceChangeListener(specificGravityAndWaterAbsorptionFine, 250);
        setupPriceChangeListener(soundnessFine, 350);
        setupPriceChangeListener(alkaliReactivityFine, 350);

        //price Aggregate Coarse listeners
        setupPriceChangeListener(cbGradingOfAggregateCoarse, 250);
        setupPriceChangeListener(cbFlakinessIndexAndElongationIndexCoarse, 250);
        setupPriceChangeListener(SpecificGravityAndWaterAbsorptionCoarse, 350);
        setupPriceChangeListener(cbCrushingValueCoarse, 350);
        setupPriceChangeListener(cbSoundnessCyclesCoarse, 350);
        setupPriceChangeListener(cbAbrasionValueCoarse, 350);
        setupPriceChangeListener(cbImpactValueCoarse, 350);
        setupPriceChangeListener(cbAbrasionValueCoarse, 350);
        setupPriceChangeListener(cbAlkaliReactivityCoarse, 400);

        //price Power Block listeners
        setupPriceChangeListener(cbWaterAbsorptionPaver, 250);
        setupPriceChangeListener(cbCompressiveStrengthPaver, 350);

        //price Cement listeners
        setupPriceChangeListener(cbConsistencyCement, 250);
        setupPriceChangeListener(cbInitialSettingTimeCement, 350);
        setupPriceChangeListener(cbFinessByBlainCement, 350);
        setupPriceChangeListener(cbFinenessCement, 350);
        setupPriceChangeListener(cbCompressiveCement, 350);
        setupPriceChangeListener(cbSoundenessCemenet, 350);
        setupPriceChangeListener(cbCementCompressiveStrengthMortar, 350);
        setupPriceChangeListener(cbCementChemicalAnalysis, 350);


        //price Hardened Concrete listeners
        setupPriceChangeListener(cbCompressiveStrengthConcrete, 250);

        //price Brick listeners
        setupPriceChangeListener(cbCompressiveStrengthBrick, 350);
        setupPriceChangeListener(cbDimensionTestBrick, 350);
        setupPriceChangeListener(cbWaterAbsorptionBrick, 350);
        setupPriceChangeListener(cbBrickEfflorescence, 400);

        //price Soil listeners
        setupPriceChangeListener(cbSoilCBRTestUnsoaked, 250);
        setupPriceChangeListener(cbGrainSizeAnalysisSoil, 350);
        setupPriceChangeListener(cbTestSoaked, 350);
        setupPriceChangeListener(cbPlasticLimitSoil, 350);
        setupPriceChangeListener(cbLightCompactionTestSoil, 350);
        setupPriceChangeListener(cbHeavyCompactionTestSoil, 350);
        setupPriceChangeListener(cbFreeSwellIndexSoil, 250);
        setupPriceChangeListener(cbShrinkageLimitSoil, 350);
        setupPriceChangeListener(cbDirectShearSoil, 350);
        setupPriceChangeListener(cbPermeabilityTestSoil, 350);
        setupPriceChangeListener(cbRelativeDensitySoil, 350);
        setupPriceChangeListener(cbFieldDensityAndMoistureContentSoil, 350);
        setupPriceChangeListener(cbConsolidationSoil, 350);
        setupPriceChangeListener(cbUnconfinedCompressionSoil, 400);
        setupPriceChangeListener(cbTriaxialTestUUSoil, 350);
        setupPriceChangeListener(cbTriaxialTestCUSoil, 350);
        setupPriceChangeListener(cbSpecificGravitySoil, 500);
        setupPriceChangeListener(cbSwellingPressureSoil, 350);

        //price Steel Bar listeners
        setupPriceChangeListener(cbMeasurementOfDimensionsAac, 250);
        setupPriceChangeListener(cbCompressiveStrengthAac, 350);
        setupPriceChangeListener(cbBlocksDensityAac, 350);
        setupPriceChangeListener(cbWaterAbsorptionAac, 350);
        setupPriceChangeListener(cbDryingShrinkageAac, 350);
        setupPriceChangeListener(cbMoistureMovementAac, 350);

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
                finenessModulusBygradationFine.setVisibility(View.VISIBLE);
                siltContentFine.setVisibility(View.VISIBLE);
                specificGravityAndWaterAbsorptionFine.setVisibility(View.VISIBLE);
                soundnessFine.setVisibility(View.VISIBLE);
                alkaliReactivityFine.setVisibility(View.VISIBLE);


            } else {
                // Hide the Aggregate Fine quantity input and other checkboxes
                tilAggregateFineQuantity.setVisibility(View.GONE);
                finenessModulusBygradationFine.setVisibility(View.GONE);
                siltContentFine.setVisibility(View.GONE);
                specificGravityAndWaterAbsorptionFine.setVisibility(View.GONE);
                soundnessFine.setVisibility(View.GONE);
                alkaliReactivityFine.setVisibility(View.GONE);

                // Uncheck the additional checkboxes when hiding them
                finenessModulusBygradationFine.setChecked(false);
                siltContentFine.setChecked(false);
                specificGravityAndWaterAbsorptionFine.setChecked(false);
                soundnessFine.setChecked(false);
                alkaliReactivityFine.setChecked(false);
            }
        });

        // Special setup for Aggregate Coarse checkbox
        cbAggregateCoarse.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all the related checkboxes
                tilAggregateCoarseQuantity.setVisibility(View.VISIBLE);
                cbGradingOfAggregateCoarse.setVisibility(View.VISIBLE);
                cbFlakinessIndexAndElongationIndexCoarse.setVisibility(View.VISIBLE);
                SpecificGravityAndWaterAbsorptionCoarse.setVisibility(View.VISIBLE);
                cbImpactValueCoarse.setVisibility(View.VISIBLE);
                cbAbrasionValueCoarse.setVisibility(View.VISIBLE);
                cbCrushingValueCoarse.setVisibility(View.VISIBLE);
                cbSoundnessCyclesCoarse.setVisibility(View.VISIBLE);
                cbAlkaliReactivityCoarse.setVisibility(View.VISIBLE);
            } else {
                // Hide all the related checkboxes and uncheck them
                tilAggregateCoarseQuantity.setVisibility(View.GONE);
                cbGradingOfAggregateCoarse.setVisibility(View.GONE);
                cbFlakinessIndexAndElongationIndexCoarse.setVisibility(View.GONE);
                SpecificGravityAndWaterAbsorptionCoarse.setVisibility(View.GONE);
                cbImpactValueCoarse.setVisibility(View.GONE);
                cbAbrasionValueCoarse.setVisibility(View.GONE);
                cbCrushingValueCoarse.setVisibility(View.GONE);
                cbSoundnessCyclesCoarse.setVisibility(View.GONE);
                cbAlkaliReactivityCoarse.setVisibility(View.GONE);

                cbGradingOfAggregateCoarse.setChecked(false);
                cbFlakinessIndexAndElongationIndexCoarse.setChecked(false);
                SpecificGravityAndWaterAbsorptionCoarse.setChecked(false);
                cbImpactValueCoarse.setChecked(false);
                cbAbrasionValueCoarse.setChecked(false);
                cbCrushingValueCoarse.setChecked(false);
                cbSoundnessCyclesCoarse.setChecked(false);
                cbAlkaliReactivityCoarse.setChecked(false);
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
                cbFinessByBlainCement.setVisibility(View.VISIBLE);
                cbFinenessCement.setVisibility(View.VISIBLE);
                cbCompressiveCement.setVisibility(View.VISIBLE);
                cbSoundenessCemenet.setVisibility(View.VISIBLE);
                cbCementCompressiveStrengthMortar.setVisibility(View.VISIBLE);
                cbCementChemicalAnalysis.setVisibility(View.VISIBLE);
            } else {
                // Hide all related checkboxes when cbCement is unchecked
                tilCementQuantity.setVisibility(View.GONE);
                cbConsistencyCement.setVisibility(View.GONE);
                cbInitialSettingTimeCement.setVisibility(View.GONE);
                cbFinessByBlainCement.setVisibility(View.GONE);
                cbFinenessCement.setVisibility(View.GONE);
                cbCompressiveCement.setVisibility(View.GONE);
                cbSoundenessCemenet.setVisibility(View.GONE);
                cbCementCompressiveStrengthMortar.setVisibility(View.GONE);
                cbCementChemicalAnalysis.setVisibility(View.GONE);

                // Uncheck them as well
                cbConsistencyCement.setChecked(false);
                cbInitialSettingTimeCement.setChecked(false);
                cbFinessByBlainCement.setChecked(false);
                cbFinenessCement.setChecked(false);
                cbCompressiveCement.setChecked(false);
                cbSoundenessCemenet.setChecked(false);
                cbCementCompressiveStrengthMortar.setChecked(false);
                cbCementChemicalAnalysis.setChecked(false);
            }
        });

        cbSteel.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show the Compressive Strength checkbox when cbConcrete is checked
                tilSteelQuantity.setVisibility(View.VISIBLE);
                cbCompressiveStrengthConcrete.setVisibility(View.VISIBLE);
            } else {
                // Hide the Compressive Strength checkbox when cbConcrete is unchecked
                tilSteelQuantity.setVisibility(View.GONE);
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
                cbBrickEfflorescence.setVisibility(View.VISIBLE);
            } else {
                // Hide all Brick-related checkboxes when cbBrick is unchecked
                tilBrickQuantity.setVisibility(View.GONE);
                cbCompressiveStrengthBrick.setVisibility(View.GONE);
                cbDimensionTestBrick.setVisibility(View.GONE);
                cbWaterAbsorptionBrick.setVisibility(View.GONE);
                cbBrickEfflorescence.setVisibility(View.GONE);

                // Uncheck all related checkboxes
                cbCompressiveStrengthBrick.setChecked(false);
                cbDimensionTestBrick.setChecked(false);
                cbWaterAbsorptionBrick.setChecked(false);
                cbBrickEfflorescence.setChecked(false);
            }
        });

        // Set up listener for the Soil checkbox
        cbSoil.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Soil-related checkboxes when cbSoil is checked
                tilSoilQuantity.setVisibility(View.VISIBLE);
                cbSoilCBRTestUnsoaked.setVisibility(View.VISIBLE);
                cbGrainSizeAnalysisSoil.setVisibility(View.VISIBLE);
                cbTestSoaked.setVisibility(View.VISIBLE);
                cbPlasticLimitSoil.setVisibility(View.VISIBLE);
                cbLightCompactionTestSoil.setVisibility(View.VISIBLE);
                cbHeavyCompactionTestSoil.setVisibility(View.VISIBLE);
                cbFreeSwellIndexSoil.setVisibility(View.VISIBLE);
                cbShrinkageLimitSoil.setVisibility(View.VISIBLE);
                cbDirectShearSoil.setVisibility(View.VISIBLE);
                cbPermeabilityTestSoil.setVisibility(View.VISIBLE);
                cbRelativeDensitySoil.setVisibility(View.VISIBLE);
                cbFieldDensityAndMoistureContentSoil.setVisibility(View.VISIBLE);
                cbConsolidationSoil.setVisibility(View.VISIBLE);
                cbUnconfinedCompressionSoil.setVisibility(View.VISIBLE);
                cbTriaxialTestUUSoil.setVisibility(View.VISIBLE);
                cbTriaxialTestCUSoil.setVisibility(View.VISIBLE);
                cbSwellingPressureSoil.setVisibility(View.VISIBLE);
                cbSpecificGravitySoil.setVisibility(View.VISIBLE);

            } else {
                // Hide all Soil-related checkboxes when cbSoil is unchecked
                tilSoilQuantity.setVisibility(View.GONE);
                cbSoilCBRTestUnsoaked.setVisibility(View.GONE);
                cbGrainSizeAnalysisSoil.setVisibility(View.GONE);
                cbTestSoaked.setVisibility(View.GONE);
                cbPlasticLimitSoil.setVisibility(View.GONE);
                cbLightCompactionTestSoil.setVisibility(View.GONE);
                cbHeavyCompactionTestSoil.setVisibility(View.GONE);
                cbFreeSwellIndexSoil.setVisibility(View.GONE);
                cbShrinkageLimitSoil.setVisibility(View.GONE);
                cbDirectShearSoil.setVisibility(View.GONE);
                cbPermeabilityTestSoil.setVisibility(View.GONE);
                cbRelativeDensitySoil.setVisibility(View.GONE);
                cbFieldDensityAndMoistureContentSoil.setVisibility(View.GONE);
                cbConsolidationSoil.setVisibility(View.GONE);
                cbUnconfinedCompressionSoil.setVisibility(View.GONE);
                cbTriaxialTestUUSoil.setVisibility(View.GONE);
                cbTriaxialTestCUSoil.setVisibility(View.GONE);
                cbSwellingPressureSoil.setVisibility(View.GONE);
                cbSpecificGravitySoil.setVisibility(View.GONE);

                cbSoilCBRTestUnsoaked.setChecked(false);
                cbGrainSizeAnalysisSoil.setChecked(false);
                cbTestSoaked.setChecked(false);
                cbPlasticLimitSoil.setChecked(false);
                cbLightCompactionTestSoil.setChecked(false);
                cbHeavyCompactionTestSoil.setChecked(false);
                cbFreeSwellIndexSoil.setChecked(false);
                cbShrinkageLimitSoil.setChecked(false);
                cbDirectShearSoil.setChecked(false);
                cbPermeabilityTestSoil.setChecked(false);
                cbRelativeDensitySoil.setChecked(false);
                cbFieldDensityAndMoistureContentSoil.setChecked(false);
                cbConsolidationSoil.setChecked(false);
                cbUnconfinedCompressionSoil.setChecked(false);
                cbTriaxialTestUUSoil.setChecked(false);
                cbTriaxialTestCUSoil.setChecked(false);
                cbSwellingPressureSoil.setChecked(false);
                cbSpecificGravitySoil.setChecked(false);
            }
        });

        // Set up listeners for the Bar checkboxes if needed
        cbAacBlock.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Bar-related checkboxes when Ultimate Tensile Strength is checked
                tilAacBlockQuantity.setVisibility(View.VISIBLE);
                cbMeasurementOfDimensionsAac.setVisibility(View.VISIBLE);
                cbCompressiveStrengthAac.setVisibility(View.VISIBLE);
                cbBlocksDensityAac.setVisibility(View.VISIBLE);
                cbWaterAbsorptionAac.setVisibility(View.VISIBLE);
                cbDryingShrinkageAac.setVisibility(View.VISIBLE);
                cbMoistureMovementAac.setVisibility(View.VISIBLE);
            } else {
                // Hide all Bar-related checkboxes when it's unchecked
                tilAacBlockQuantity.setVisibility(View.GONE);
                cbMeasurementOfDimensionsAac.setVisibility(View.GONE);
                cbCompressiveStrengthAac.setVisibility(View.GONE);
                cbBlocksDensityAac.setVisibility(View.GONE);
                cbWaterAbsorptionAac.setVisibility(View.GONE);
                cbDryingShrinkageAac.setVisibility(View.GONE);
                cbMoistureMovementAac.setVisibility(View.GONE);

                cbMeasurementOfDimensionsAac.setChecked(false);
                cbCompressiveStrengthAac.setChecked(false);
                cbBlocksDensityAac.setChecked(false);
                cbWaterAbsorptionAac.setChecked(false);
                cbDryingShrinkageAac.setChecked(false);
                cbMoistureMovementAac.setChecked(false);
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

                cbAbsoluteViscosityBitumen.setChecked(false);
                cbKinematicViscosityBitumen.setChecked(false);
                cbPenetrationValueBitumen.setChecked(false);
                cbSofteningPointBitumen.setChecked(false);
                cbDuctilityBitumen.setChecked(false);
                cbSpecificGravityBitumen.setChecked(false);
                cbLossOnHeatingBitumen.setChecked(false);
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

                cbUltrasonicPulseVelocityNDT.setChecked(false);
                cbReboundHammerTestNDT.setChecked(false);
            }
        });


        return view;
    }

    private CheckBox getSpecificGravityAndWaterAbsorptionFine() {
        return specificGravityAndWaterAbsorptionFine;
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