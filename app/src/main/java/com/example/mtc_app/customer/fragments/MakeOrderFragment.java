package com.example.mtc_app.customer.fragments;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mtc_app.MainActivity;
import com.example.mtc_app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MakeOrderFragment extends Fragment {

    private CheckBox cbCementCube, cbPowerBlock, cbSteel, cbBrick, cbSoil, cbAacBlock, cbConstWater, cbWasteWater, cbMixDesign;
    private CheckBox cbAggregateFine, cbAggregateCoarse, cbCement, cbNDT, cbFlyAsh, cbBitumen;

    // Aggregate Fine
    private CheckBox finenessModulusBygradationFine, siltContentFine, specificGravityAndWaterAbsorptionFine, soundnessFine, alkaliReactivityFine;

    //Aggregate Coarse
    private CheckBox cbImpactValueCoarse, SpecificGravityAndWaterAbsorptionCoarse, cbCrushingValueCoarse, cbSoundnessCyclesCoarse,  cbFlakinessIndexAndElongationIndexCoarse, cbGradingOfAggregateCoarse, cbAbrasionValueCoarse, cbAlkaliReactivityCoarse;

    //Paver Block
    private CheckBox cbCompressiveStrengthPaver, cbWaterAbsorptionPaver;

    //Cement
    private CheckBox cbFinessByBlainCement, cbInitialSettingTimeCement, cbConsistencyCement, cbCompressiveCement, cbFinenessCement,cbSoundenessCemenet, cbCompressiveStrengthMortarCement, cbChemicalAnalysisCement;

    //Steel
    private CheckBox cbUnitWaitSteel, cbEnsileTestYieldAndElogationTestSteel, cbBendTestSteel, cbRebendTestSteel, cbChemicalAnalysisSteel;
    //Brick
    private CheckBox cbWaterAbsorptionBrick,cbDimensionTestBrick, cbCompressiveStrengthBrick, cbEfflorescenceBrick;

    //Soil
    private CheckBox cbCBRTestUnsoakedSoil, cbGrainSizeAnalysisSoil, cbTestSoakedSoil, cbPlasticLimitSoil,
            cbLightCompactionTestSoil, cbHeavyCompactionTestSoil, cbFreeSwellIndexSoil, cbUnconfinedCompressionSoil, cbTriaxialTestUUSoil, cbTriaxialTestCUSoil,  cbSwellingPressureSoil, cbSpecificGravitySoil, cbShrinkageLimitSoil,
            cbDirectShearSoil, cbPermeabilityTestSoil, cbRelativeDensitySoil, cbFieldDensityAndMoistureContentSoil, cbConsolidationSoil;

    //AAC & NDT
    private CheckBox cbMeasurementOfDimensionsAac, cbCompressiveStrengthAac, cbBlocksDensityAac, cbWaterAbsorptionAac,
            cbDryingShrinkageAac, cbMoistureMovementAac,cbUltrasonicPulseVelocityNDT, cbReboundHammerTestNDT;

    //CEMENT CONC.CUBE
    private CheckBox cbCompressiveStrengthOfCube, cbCastingPreparingCubesOfGivenMixCube, cbFlexurerStrengthOfBeamCube, cbCastingPreparingBeamCube;

    //Const. Water
    private CheckBox cbSulphatesSO4CWater, cbAlkalinityCWater, cbPHValueCWater, cbOrganicImpuritiesCWater, cbInorganicImpuritiesCWater, cbChlorideAsClCWater, cbSuspendedMatterCWater, cbTDSTotalDissolvedSolidsCWater;

    //Waste Water
    private CheckBox cbChlorideAsClWasteWater, cbPhValueWasteWater, cbSulphatesSo4WasteWater, cbTdsTotalDissolvedSolidsWasteWater, cbTssTotalSuspendedSolidsWasteWater;

    // Concrete Mix Design
    private CheckBox cbWithCubeMixDesign, cbWithFlexurerStrengthMixDesign, cbWithAdmixtureMixDesign;

    // FLY ASH Checkbox
    private CheckBox cbSpecificGravityFlyAsh, cbSoundnessFlyAsh, cbCompressiveStrengthFlyAsh;

    // Bitumen
    private CheckBox  cbPenetrationValueBitumen, cbSofteningPointBitumen, cbDuctilityBitumen, cbSpecificGravityBitumen;
    private TextInputLayout tilPowerBlockQuantity, tilSteelQuantity, tillCemenetCubeQuantity, tilBrickQuantity, tilSoilQuantity, tilAacBlockQuantity;
    private TextInputLayout tilAggregateFineQuantity, tilAggregateCoarseQuantity, tilCementQuantity, tillConstWaterQuantity, tilWasteWaterQuantity, tillFlyAsh, tilMixDesignQuantity, tilBitumenQuantity, tilNDTQuantity;

    // Date Input
    // Prices for each item
    private final Map<CheckBox, Integer> priceMap = new HashMap<>();
    private TextView tvTotalPrice;
    private int totalPrice = 0;

    //Database variables
    private EditText customerNameField, dispatchAddressField, mobileNumberField, emailField;
    private RadioGroup modeOfDispatchGroup;
    private Button submitButton;
    private EditText termsAndConditionsField;
    private LinearLayout pointsGroup; // Assuming pointsGroup contains CheckBoxes in a LinearLayout
    private RadioGroup sampleConditionGroup;
    private RadioGroup complianceStatementGroup;
    private RadioGroup standardDeviationGroup;
    private boolean isSubmitting = false;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_make_order_fragment, container, false);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize views
        customerNameField = view.findViewById(R.id.customer_name);
        dispatchAddressField = view.findViewById(R.id.dispatch_address);
        mobileNumberField = view.findViewById(R.id.mobile_number);
        emailField = view.findViewById(R.id.email);
        termsAndConditionsField = view.findViewById(R.id.terms_and_conditions);
        modeOfDispatchGroup = view.findViewById(R.id.mode_of_dispatch_group);
        pointsGroup = view.findViewById(R.id.points_group);
        sampleConditionGroup = view.findViewById(R.id.sample_condition_group);
        complianceStatementGroup = view.findViewById(R.id.compliance_statement_group);
        standardDeviationGroup = view.findViewById(R.id.standard_deviation_group);
        submitButton = view.findViewById(R.id.submit_button);

        // Set click listener for the button
        submitButton.setOnClickListener(v -> {
            if (!isSubmitting) {
                submitData();
            }
        });

        // Initialize CheckBoxes
        cbPowerBlock = view.findViewById(R.id.cb_power_block);
        cbSteel = view.findViewById(R.id.cb_steel);
        cbConstWater = view.findViewById(R.id.cb_const_water);
        cbWasteWater = view.findViewById(R.id.cb_waste_water);
        cbFlyAsh = view.findViewById(R.id.cb_fly_ash);
        cbMixDesign = view.findViewById(R.id.cb_mix_design);
        cbBrick = view.findViewById(R.id.cb_brick);
        cbSoil = view.findViewById(R.id.cb_soil);
        cbCementCube = view.findViewById(R.id.cb_cement_cube);
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

        // Initialize Paver Block checkboxes
        cbCompressiveStrengthPaver = view.findViewById(R.id.PAVER_compressive_strength);
        cbWaterAbsorptionPaver = view.findViewById(R.id.PAVER_water_absorption);

        // Initialize TextInputLayouts for All Quantity
        tilPowerBlockQuantity = view.findViewById(R.id.til_power_block_quantity);
        tilSteelQuantity = view.findViewById(R.id.til_steel_quantity);
        tillConstWaterQuantity = view.findViewById(R.id.til_const_water_quantity);
        tilBrickQuantity = view.findViewById(R.id.til_brick_quantity);
        tilSoilQuantity = view.findViewById(R.id.til_soil_quantity);
        tilAacBlockQuantity = view.findViewById(R.id.til_aac_block_quantity);
        tillCemenetCubeQuantity = view.findViewById(R.id.til_cement_cube_quantity);
        tilAggregateFineQuantity = view.findViewById(R.id.til_aggregate_fine_quantity);
        tilAggregateCoarseQuantity = view.findViewById(R.id.til_aggregate_coarse_quantity);
        tilCementQuantity = view.findViewById(R.id.til_cement_quantity);
        tillConstWaterQuantity = view.findViewById(R.id.til_const_water_quantity);
        tilWasteWaterQuantity = view.findViewById(R.id.til_waste_water_quantity);
        tillFlyAsh = view.findViewById(R.id.til_fly_ash_quantity);
        tilMixDesignQuantity = view.findViewById(R.id.til_mix_water_quantity);
        tilBitumenQuantity = view.findViewById(R.id.til_bitumen_quantity);
        tilNDTQuantity = view.findViewById(R.id.til_ndt_quantity);

        // Initialize Cement checkbox
        cbConsistencyCement = view.findViewById(R.id.CEMENT_consistency);
        cbInitialSettingTimeCement = view.findViewById(R.id.CEMENT_initial_setting_time);
        cbFinessByBlainCement = view.findViewById(R.id.CEMENT_finess_by_blain);
        cbFinenessCement = view.findViewById(R.id.CEMENT_fineness);
        cbCompressiveCement = view.findViewById(R.id.CEMENT_compressive);
        cbSoundenessCemenet = view.findViewById(R.id.CEMENT_soundness);
        cbCompressiveStrengthMortarCement = view.findViewById(R.id.CEMENT_COMPRESSIVE_STRENGTH_MORTAR);
        cbChemicalAnalysisCement = view.findViewById(R.id.cement_chemical_analysis);

        // Initialize Steel checkbox
        cbUnitWaitSteel = view.findViewById(R.id.STEEL_unit_wait);
        cbEnsileTestYieldAndElogationTestSteel = view.findViewById(R.id.STEEL_ensile_test_yield_and_elogation_test);
        cbBendTestSteel = view.findViewById(R.id.STEEL_bend_test);
        cbRebendTestSteel = view.findViewById(R.id.STEEL_rebend_test);
        cbChemicalAnalysisSteel = view.findViewById(R.id.STEEL_chemical_analysis);

        // Initialize Brick checkbox
        cbCompressiveStrengthBrick = view.findViewById(R.id.BRICK_compressive_strength);
        cbDimensionTestBrick = view.findViewById(R.id.BRICK_dimension_test);
        cbWaterAbsorptionBrick = view.findViewById(R.id.BRICK_Fine_water_absorption);
        cbEfflorescenceBrick = view.findViewById(R.id.BRICK_efflorescence);

        // Initialize Soil checkbox and its related checkboxes
        cbCBRTestUnsoakedSoil = view.findViewById(R.id.SOIL_cbr_test_unsoaked);
        cbGrainSizeAnalysisSoil = view.findViewById(R.id.SOIL_grain_size_analysis);
        cbTestSoakedSoil = view.findViewById(R.id.SOIL_cbr_test_soaked);
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

        // Initialize AAC checkboxes
        cbMeasurementOfDimensionsAac = view.findViewById(R.id.AAC_measurement_of_dimensions);
        cbCompressiveStrengthAac = view.findViewById(R.id.AAC_compressive_strength);
        cbBlocksDensityAac = view.findViewById(R.id.AAC_blocks_density);
        cbWaterAbsorptionAac = view.findViewById(R.id.AAC_water_absorption);
        cbDryingShrinkageAac = view.findViewById(R.id.AAC_drying_shrinkage);
        cbMoistureMovementAac = view.findViewById(R.id.AAC_moisture_movement);

        // Initialize Cement Cube checkboxes
        cbCompressiveStrengthOfCube = view.findViewById(R.id.CUBE_compressive_strength_of_cube);
        cbCastingPreparingCubesOfGivenMixCube = view.findViewById(R.id.CUBE_casting_preparing_cubes_of_given_mix);
        cbFlexurerStrengthOfBeamCube = view.findViewById(R.id.CUBE_flexurer_strength_of_beam);
        cbCastingPreparingBeamCube = view.findViewById(R.id.CUBE_casting_preparing_beam);

        // Initialize Constant Water checkbox
        cbSulphatesSO4CWater = view.findViewById(R.id.C_WATER_sulphates_so4);
        cbAlkalinityCWater = view.findViewById(R.id.C_WATER_alkalinity);
        cbPHValueCWater = view.findViewById(R.id.C_WATER_ph_value);
        cbOrganicImpuritiesCWater = view.findViewById(R.id.C_WATER_organic_impurities);
        cbInorganicImpuritiesCWater = view.findViewById(R.id.C_WATER_inorganic_impurities);
        cbChlorideAsClCWater = view.findViewById(R.id.C_WATER_chloride_as_cl);
        cbSuspendedMatterCWater = view.findViewById(R.id.C_WATER_suspended_matter);
        cbTDSTotalDissolvedSolidsCWater = view.findViewById(R.id.C_WATER_tds_total_dissolved_solids);

        // Initialize Waste Water checkbox
        cbChlorideAsClWasteWater = view.findViewById(R.id.WASTE_WATER_chloride_as_cl);
        cbPhValueWasteWater = view.findViewById(R.id.WASTE_WATER_ph_value);
        cbSulphatesSo4WasteWater = view.findViewById(R.id.WASTE_WATER_sulphates_so4);
        cbTdsTotalDissolvedSolidsWasteWater = view.findViewById(R.id.WASTE_WATER_tds_total_dissolved_solids);
        cbTssTotalSuspendedSolidsWasteWater = view.findViewById(R.id.WASTE_WATER_tss_total_suspended_solids);

        // Initialize Concrete Mix Design checkbox
        cbWithCubeMixDesign = view.findViewById(R.id.MIX_DESIGN_with_cube);
        cbWithFlexurerStrengthMixDesign = view.findViewById(R.id.MIX_DESIGN_with_flexurer_strength);
        cbWithAdmixtureMixDesign = view.findViewById(R.id.MIX_DESIGN_with_admixture);

        // Initialize Fly Ash checkbox
        cbSpecificGravityFlyAsh = view.findViewById(R.id.FLY_ASH_specific_gravity);
        cbSoundnessFlyAsh = view.findViewById(R.id.FLY_ASH_soundness);
        cbCompressiveStrengthFlyAsh = view.findViewById(R.id.FLY_ASH_compressive_strength);

        // Bitumen checkboxes
        cbPenetrationValueBitumen = view.findViewById(R.id.BITUMEN_penetration_value);
        cbSofteningPointBitumen = view.findViewById(R.id.BITUMEN_softening_point);
        cbDuctilityBitumen = view.findViewById(R.id.BITUMEN_ductility);
        cbSpecificGravityBitumen = view.findViewById(R.id.BITUMEN_specific_gravity);

        // Initialize NDT checkboxes
        cbUltrasonicPulseVelocityNDT = view.findViewById(R.id.NDT_ultrasonic_pulse_velocity);
        cbReboundHammerTestNDT = view.findViewById(R.id.NDT_rebound_hammer_test);

        // Set up for All checkboxes
        setUpCheckboxListener(cbPowerBlock, tilPowerBlockQuantity);
        setUpCheckboxListener(cbSteel, tilSteelQuantity);
        setUpCheckboxListener(cbBrick, tilBrickQuantity);
        setUpCheckboxListener(cbSoil, tilSoilQuantity);
        setUpCheckboxListener(cbAacBlock, tilAacBlockQuantity);
        setUpCheckboxListener(cbCementCube, tillCemenetCubeQuantity);
        setUpCheckboxListener(cbAggregateCoarse, tilAggregateCoarseQuantity);
        setUpCheckboxListener(cbCement, tilCementQuantity);
        setUpCheckboxListener(cbConstWater, tillConstWaterQuantity);
        setUpCheckboxListener(cbWasteWater, tilWasteWaterQuantity);
        setUpCheckboxListener(cbMixDesign, tilMixDesignQuantity);
        setUpCheckboxListener(cbFlyAsh, tillFlyAsh);
        setUpCheckboxListener(cbNDT, tilNDTQuantity);

        //price Aggregate Fine listeners
        setupPriceChangeListener(finenessModulusBygradationFine, 250);
        setupPriceChangeListener(siltContentFine, 200);
        setupPriceChangeListener(specificGravityAndWaterAbsorptionFine, 350);
        setupPriceChangeListener(soundnessFine, 1200);
        setupPriceChangeListener(alkaliReactivityFine, 1500);

        //price Aggregate Coarse listeners
        setupPriceChangeListener(cbGradingOfAggregateCoarse, 250);
        setupPriceChangeListener(cbFlakinessIndexAndElongationIndexCoarse, 350);
        setupPriceChangeListener(SpecificGravityAndWaterAbsorptionCoarse, 350);
        setupPriceChangeListener(cbCrushingValueCoarse, 350);
        setupPriceChangeListener(cbSoundnessCyclesCoarse, 1200);
        setupPriceChangeListener(cbAbrasionValueCoarse, 350);
        setupPriceChangeListener(cbImpactValueCoarse, 250);
        setupPriceChangeListener(cbAlkaliReactivityCoarse, 1500);

        //price Power Block listeners
        setupPriceChangeListener(cbWaterAbsorptionPaver, 200);
        setupPriceChangeListener(cbCompressiveStrengthPaver, 700);

        //price Cement listeners
        setupPriceChangeListener(cbConsistencyCement, 300);
        setupPriceChangeListener(cbInitialSettingTimeCement, 400);
        setupPriceChangeListener(cbFinessByBlainCement, 600);
        setupPriceChangeListener(cbFinenessCement, 250);
        setupPriceChangeListener(cbCompressiveCement, 550);
        setupPriceChangeListener(cbSoundenessCemenet, 250);
        setupPriceChangeListener(cbCompressiveStrengthMortarCement, 200);
        setupPriceChangeListener(cbChemicalAnalysisCement, 2250);


        //price Steel listeners
        setupPriceChangeListener(cbUnitWaitSteel, 50);
        setupPriceChangeListener(cbEnsileTestYieldAndElogationTestSteel, 400);
        setupPriceChangeListener(cbBendTestSteel, 150);
        setupPriceChangeListener(cbRebendTestSteel, 150);
        setupPriceChangeListener(cbChemicalAnalysisSteel, 900);

        //price Brick listeners
        setupPriceChangeListener(cbCompressiveStrengthBrick, 400);
        setupPriceChangeListener(cbDimensionTestBrick, 150);
        setupPriceChangeListener(cbWaterAbsorptionBrick, 250);
        setupPriceChangeListener(cbEfflorescenceBrick, 200);

        //price Soil listeners
        setupPriceChangeListener(cbCBRTestUnsoakedSoil, 600);
        setupPriceChangeListener(cbGrainSizeAnalysisSoil, 300);
        setupPriceChangeListener(cbTestSoakedSoil, 900);
        setupPriceChangeListener(cbPlasticLimitSoil, 350);
        setupPriceChangeListener(cbLightCompactionTestSoil, 600);
        setupPriceChangeListener(cbHeavyCompactionTestSoil, 900);
        setupPriceChangeListener(cbFreeSwellIndexSoil, 150);
        setupPriceChangeListener(cbShrinkageLimitSoil, 300);
        setupPriceChangeListener(cbDirectShearSoil, 700);
        setupPriceChangeListener(cbPermeabilityTestSoil, 900);
        setupPriceChangeListener(cbRelativeDensitySoil, 200);
        setupPriceChangeListener(cbFieldDensityAndMoistureContentSoil, 600);
        setupPriceChangeListener(cbConsolidationSoil, 1200);
        setupPriceChangeListener(cbUnconfinedCompressionSoil, 300);
        setupPriceChangeListener(cbTriaxialTestUUSoil, 900);
        setupPriceChangeListener(cbTriaxialTestCUSoil, 1500);
        setupPriceChangeListener(cbSpecificGravitySoil, 200);
        setupPriceChangeListener(cbSwellingPressureSoil, 900);

        //price AAC listeners
        setupPriceChangeListener(cbMeasurementOfDimensionsAac, 1200);
        setupPriceChangeListener(cbCompressiveStrengthAac, 1200);
        setupPriceChangeListener(cbBlocksDensityAac, 1200);
        setupPriceChangeListener(cbWaterAbsorptionAac, 1200);
        setupPriceChangeListener(cbDryingShrinkageAac, 350);
        setupPriceChangeListener(cbMoistureMovementAac, 200);

        //price Const Water listeners
        setupPriceChangeListener(cbSulphatesSO4CWater, 900);
        setupPriceChangeListener(cbAlkalinityCWater, 900);
        setupPriceChangeListener(cbPHValueCWater, 900);
        setupPriceChangeListener(cbOrganicImpuritiesCWater, 900);
        setupPriceChangeListener(cbInorganicImpuritiesCWater, 900);
        setupPriceChangeListener(cbChlorideAsClCWater, 900);
        setupPriceChangeListener(cbSuspendedMatterCWater, 900);
        setupPriceChangeListener(cbTDSTotalDissolvedSolidsCWater, 900);

        //price Waste Water listeners
        setupPriceChangeListener(cbChlorideAsClWasteWater, 700);
        setupPriceChangeListener(cbPhValueWasteWater, 700);
        setupPriceChangeListener(cbSulphatesSo4WasteWater, 700);
        setupPriceChangeListener(cbTdsTotalDissolvedSolidsWasteWater, 700);
        setupPriceChangeListener(cbTssTotalSuspendedSolidsWasteWater, 700);

        //price Concrete Mix Design listeners
        setupPriceChangeListener(cbWithCubeMixDesign, 6000);
        setupPriceChangeListener(cbWithFlexurerStrengthMixDesign, 7500);
        setupPriceChangeListener(cbWithAdmixtureMixDesign, 9000);

        //price Cement Cube listeners
        setupPriceChangeListener(cbCompressiveStrengthOfCube, 400);
        setupPriceChangeListener(cbCastingPreparingCubesOfGivenMixCube, 1000);
        setupPriceChangeListener(cbFlexurerStrengthOfBeamCube, 450);
        setupPriceChangeListener(cbCastingPreparingBeamCube, 900);

        //price Fly Ash listeners
        setupPriceChangeListener(cbSpecificGravityFlyAsh, 200);
        setupPriceChangeListener(cbSoundnessFlyAsh, 250);
        setupPriceChangeListener(cbCompressiveStrengthFlyAsh, 550);

        //price Bitumen listeners
        setupPriceChangeListener(cbPenetrationValueBitumen, 1100);
        setupPriceChangeListener(cbSofteningPointBitumen, 1500);
        setupPriceChangeListener(cbDuctilityBitumen, 1050);
        setupPriceChangeListener(cbSpecificGravityBitumen, 350);

        //price NDT listeners
        setupPriceChangeListener(cbUltrasonicPulseVelocityNDT, 300);
        setupPriceChangeListener(cbReboundHammerTestNDT, 300);


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
                cbCompressiveStrengthMortarCement.setVisibility(View.VISIBLE);
                cbChemicalAnalysisCement.setVisibility(View.VISIBLE);
            } else {
                // Hide all related checkboxes when cbCement is unchecked
                tilCementQuantity.setVisibility(View.GONE);
                cbConsistencyCement.setVisibility(View.GONE);
                cbInitialSettingTimeCement.setVisibility(View.GONE);
                cbFinessByBlainCement.setVisibility(View.GONE);
                cbFinenessCement.setVisibility(View.GONE);
                cbCompressiveCement.setVisibility(View.GONE);
                cbSoundenessCemenet.setVisibility(View.GONE);
                cbCompressiveStrengthMortarCement.setVisibility(View.GONE);
                cbChemicalAnalysisCement.setVisibility(View.GONE);

                // Uncheck them as well
                cbConsistencyCement.setChecked(false);
                cbInitialSettingTimeCement.setChecked(false);
                cbFinessByBlainCement.setChecked(false);
                cbFinenessCement.setChecked(false);
                cbCompressiveCement.setChecked(false);
                cbSoundenessCemenet.setChecked(false);
                cbCompressiveStrengthMortarCement.setChecked(false);
                cbChemicalAnalysisCement.setChecked(false);
            }
        });

        // Set up listener for the Steel checkbox
        cbSteel.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show the Compressive Strength checkbox when cbConcrete is checked
                tilSteelQuantity.setVisibility(View.VISIBLE);
                cbUnitWaitSteel.setVisibility(View.VISIBLE);
                cbEnsileTestYieldAndElogationTestSteel.setVisibility(View.VISIBLE);
                cbBendTestSteel.setVisibility(View.VISIBLE);
                cbRebendTestSteel.setVisibility(View.VISIBLE);
                cbChemicalAnalysisSteel.setVisibility(View.VISIBLE);

            } else {
                // Hide the Compressive Strength checkbox when cbConcrete is unchecked
                tilSteelQuantity.setVisibility(View.GONE);
                cbUnitWaitSteel.setVisibility(View.GONE);
                cbEnsileTestYieldAndElogationTestSteel.setVisibility(View.GONE);
                cbBendTestSteel.setVisibility(View.GONE);
                cbRebendTestSteel.setVisibility(View.GONE);
                cbChemicalAnalysisSteel.setVisibility(View.GONE);


                // Uncheck it as well
                cbUnitWaitSteel.setChecked(false);
                cbEnsileTestYieldAndElogationTestSteel.setChecked(false);
                cbBendTestSteel.setChecked(false);
                cbRebendTestSteel.setChecked(false);
                cbChemicalAnalysisSteel.setChecked(false);

            }
        });

        // Set up listener for Brick checkbox
        cbBrick.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Brick-related checkboxes when cbBrick is checked
                tilBrickQuantity.setVisibility(View.VISIBLE);
                cbCompressiveStrengthBrick.setVisibility(View.VISIBLE);
                cbDimensionTestBrick.setVisibility(View.VISIBLE);
                cbWaterAbsorptionBrick.setVisibility(View.VISIBLE);
                cbEfflorescenceBrick.setVisibility(View.VISIBLE);
            } else {
                // Hide all Brick-related checkboxes when cbBrick is unchecked
                tilBrickQuantity.setVisibility(View.GONE);
                cbCompressiveStrengthBrick.setVisibility(View.GONE);
                cbDimensionTestBrick.setVisibility(View.GONE);
                cbWaterAbsorptionBrick.setVisibility(View.GONE);
                cbEfflorescenceBrick.setVisibility(View.GONE);

                // Uncheck all related checkboxes
                cbCompressiveStrengthBrick.setChecked(false);
                cbDimensionTestBrick.setChecked(false);
                cbWaterAbsorptionBrick.setChecked(false);
                cbEfflorescenceBrick.setChecked(false);
            }
        });

        // Set up listener for the Soil checkbox
        cbSoil.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Soil-related checkboxes when cbSoil is checked
                tilSoilQuantity.setVisibility(View.VISIBLE);
                cbCBRTestUnsoakedSoil.setVisibility(View.VISIBLE);
                cbGrainSizeAnalysisSoil.setVisibility(View.VISIBLE);
                cbTestSoakedSoil.setVisibility(View.VISIBLE);
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
                cbCBRTestUnsoakedSoil.setVisibility(View.GONE);
                cbGrainSizeAnalysisSoil.setVisibility(View.GONE);
                cbTestSoakedSoil.setVisibility(View.GONE);
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

                cbCBRTestUnsoakedSoil.setChecked(false);
                cbGrainSizeAnalysisSoil.setChecked(false);
                cbTestSoakedSoil.setChecked(false);
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

        // Set up listeners for the AAC checkboxes if needed
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

        // Set up listeners for the Cement Cube checkboxes if needed
        cbCementCube.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Bar-related checkboxes when Ultimate Tensile Strength is checked
                tillCemenetCubeQuantity.setVisibility(View.VISIBLE);
                cbCompressiveStrengthOfCube.setVisibility(View.VISIBLE);
                cbCastingPreparingCubesOfGivenMixCube.setVisibility(View.VISIBLE);
                cbFlexurerStrengthOfBeamCube.setVisibility(View.VISIBLE);
                cbCastingPreparingBeamCube.setVisibility(View.VISIBLE);

            } else {
                // Hide all Bar-related checkboxes when it's unchecked
                tillCemenetCubeQuantity.setVisibility(View.GONE);
                cbCompressiveStrengthOfCube.setVisibility(View.GONE);
                cbCastingPreparingCubesOfGivenMixCube.setVisibility(View.GONE);
                cbFlexurerStrengthOfBeamCube.setVisibility(View.GONE);
                cbCastingPreparingBeamCube.setVisibility(View.GONE);

                cbCompressiveStrengthOfCube.setChecked(false);
                cbCastingPreparingCubesOfGivenMixCube.setChecked(false);
                cbFlexurerStrengthOfBeamCube.setChecked(false);
                cbCastingPreparingBeamCube.setChecked(false);

            }
        });

        // Set up listeners for Const Water checkboxes
        cbConstWater.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Bar-related checkboxes when Ultimate Tensile Strength is checked
                tillConstWaterQuantity.setVisibility(View.VISIBLE);
                cbSulphatesSO4CWater.setVisibility(View.VISIBLE);
                cbAlkalinityCWater.setVisibility(View.VISIBLE);
                cbPHValueCWater.setVisibility(View.VISIBLE);
                cbOrganicImpuritiesCWater.setVisibility(View.VISIBLE);
                cbInorganicImpuritiesCWater.setVisibility(View.VISIBLE);
                cbChlorideAsClCWater.setVisibility(View.VISIBLE);
                cbSuspendedMatterCWater.setVisibility(View.VISIBLE);
                cbTDSTotalDissolvedSolidsCWater.setVisibility(View.VISIBLE);
            } else {
                // Hide all Bar-related checkboxes when it's unchecked
                tillConstWaterQuantity.setVisibility(View.GONE);
                cbSulphatesSO4CWater.setVisibility(View.GONE);
                cbAlkalinityCWater.setVisibility(View.GONE);
                cbPHValueCWater.setVisibility(View.GONE);
                cbOrganicImpuritiesCWater.setVisibility(View.GONE);
                cbInorganicImpuritiesCWater.setVisibility(View.GONE);
                cbChlorideAsClCWater.setVisibility(View.GONE);
                cbSuspendedMatterCWater.setVisibility(View.GONE);
                cbTDSTotalDissolvedSolidsCWater.setVisibility(View.GONE);

                cbSulphatesSO4CWater.setChecked(false);
                cbAlkalinityCWater.setChecked(false);
                cbPHValueCWater.setChecked(false);
                cbOrganicImpuritiesCWater.setChecked(false);
                cbInorganicImpuritiesCWater.setChecked(false);
                cbChlorideAsClCWater.setChecked(false);
                cbSuspendedMatterCWater.setChecked(false);
                cbTDSTotalDissolvedSolidsCWater.setChecked(false);
            }
        });

        // Set up listeners for Waste Water checkboxes
        cbWasteWater.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Bar-related checkboxes when Ultimate Tensile Strength is checked
                tilWasteWaterQuantity.setVisibility(View.VISIBLE);
                cbChlorideAsClWasteWater.setVisibility(View.VISIBLE);
                cbPhValueWasteWater.setVisibility(View.VISIBLE);
                cbSulphatesSo4WasteWater.setVisibility(View.VISIBLE);
                cbTdsTotalDissolvedSolidsWasteWater.setVisibility(View.VISIBLE);
                cbTssTotalSuspendedSolidsWasteWater.setVisibility(View.VISIBLE);

            } else {
                // Hide all Bar-related checkboxes when it's unchecked
                tilWasteWaterQuantity.setVisibility(View.GONE);
                cbChlorideAsClWasteWater.setVisibility(View.GONE);
                cbPhValueWasteWater.setVisibility(View.GONE);
                cbSulphatesSo4WasteWater.setVisibility(View.GONE);
                cbTdsTotalDissolvedSolidsWasteWater.setVisibility(View.GONE);
                cbTssTotalSuspendedSolidsWasteWater.setVisibility(View.GONE);

                cbChlorideAsClWasteWater.setChecked(false);
                cbPhValueWasteWater.setChecked(false);
                cbSulphatesSo4WasteWater.setChecked(false);
                cbTdsTotalDissolvedSolidsWasteWater.setChecked(false);
                cbTssTotalSuspendedSolidsWasteWater.setChecked(false);
            }
        });

        // Set up listeners for CONCRETE MIX DESIGN checkboxes
        cbMixDesign.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tilMixDesignQuantity.setVisibility(View.VISIBLE);
                cbWithCubeMixDesign.setVisibility(View.VISIBLE);
                cbWithFlexurerStrengthMixDesign.setVisibility(View.VISIBLE);
                cbWithAdmixtureMixDesign.setVisibility(View.VISIBLE);

            } else {
                tilMixDesignQuantity.setVisibility(View.GONE);
                cbWithCubeMixDesign.setVisibility(View.GONE);
                cbWithFlexurerStrengthMixDesign.setVisibility(View.GONE);
                cbWithAdmixtureMixDesign.setVisibility(View.GONE);

                cbWithCubeMixDesign.setChecked(false);
                cbWithFlexurerStrengthMixDesign.setChecked(false);
                cbWithAdmixtureMixDesign.setChecked(false);

            }
        });

        // Set up listeners for Fly Ash checkboxes
        cbFlyAsh.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Bar-related checkboxes when Ultimate Tensile Strength is checked
                tillFlyAsh.setVisibility(View.VISIBLE);
                cbSpecificGravityFlyAsh.setVisibility(View.VISIBLE);
                cbSoundnessFlyAsh.setVisibility(View.VISIBLE);
                cbCompressiveStrengthFlyAsh.setVisibility(View.VISIBLE);

            } else {
                // Hide all Bar-related checkboxes when it's unchecked
                tillFlyAsh.setVisibility(View.GONE);
                cbSpecificGravityFlyAsh.setVisibility(View.GONE);
                cbSoundnessFlyAsh.setVisibility(View.GONE);
                cbCompressiveStrengthFlyAsh.setVisibility(View.GONE);

                cbSpecificGravityFlyAsh.setChecked(false);
                cbSoundnessFlyAsh.setChecked(false);
                cbCompressiveStrengthFlyAsh.setChecked(false);
            }
        });

        // Set up listener for Bitumen checkbox
        cbBitumen.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show all Bitumen-related checkboxes and quantity input when cbBitumen is checked
                tilBitumenQuantity.setVisibility(View.VISIBLE);
                cbPenetrationValueBitumen.setVisibility(View.VISIBLE);
                cbSofteningPointBitumen.setVisibility(View.VISIBLE);
                cbDuctilityBitumen.setVisibility(View.VISIBLE);
                cbSpecificGravityBitumen.setVisibility(View.VISIBLE);
            } else {
                // Hide all Bitumen-related checkboxes and quantity input when cbBitumen is unchecked
                tilBitumenQuantity.setVisibility(View.GONE);
                cbPenetrationValueBitumen.setVisibility(View.GONE);
                cbSofteningPointBitumen.setVisibility(View.GONE);
                cbDuctilityBitumen.setVisibility(View.GONE);
                cbSpecificGravityBitumen.setVisibility(View.GONE);

                cbPenetrationValueBitumen.setChecked(false);
                cbSofteningPointBitumen.setChecked(false);
                cbDuctilityBitumen.setChecked(false);
                cbSpecificGravityBitumen.setChecked(false);
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

    private void submitData() {
        // Disable the button and show progress
        isSubmitting = true;
        submitButton.setEnabled(false);

        // Collect data
        String customerName = customerNameField.getText().toString().trim();
        String dispatchAddress = dispatchAddressField.getText().toString().trim();
        String mobileNumber = mobileNumberField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String termsAndConditions = termsAndConditionsField.getText().toString().trim();

        // Validation
        if (customerName.isEmpty()) {
            showError("Please enter the customer's name");
            return;
        }
        if (dispatchAddress.isEmpty()) {
            showError("Please enter the dispatch address");
            return;
        }
        if (mobileNumber.isEmpty() || !mobileNumber.matches("\\d{10}")) {
            showError("Please enter a valid 10-digit mobile number");
            return;
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError("Please enter a valid email address");
            return;
        }
        if (termsAndConditions.isEmpty()) {
            showError("Please accept the terms and conditions");
            return;
        }

        // Get selected radio button for modeOfDispatch
        String modeOfDispatch = getSelectedRadioButtonText(modeOfDispatchGroup);
        if (modeOfDispatch.equals("")) {
            showError("Please select a mode of dispatch");
            return;
        }

        // Get selected checkboxes for pointsGroup
        ArrayList<String> selectedPoints = new ArrayList<>();
        for (int i = 0; i < pointsGroup.getChildCount(); i++) {
            View child = pointsGroup.getChildAt(i);
            if (child instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) child;
                if (checkBox.isChecked()) {
                    selectedPoints.add(checkBox.getText().toString());
                }
            }
        }

        // Get selected radio buttons for each group
        String sampleCondition = getSelectedRadioButtonText(sampleConditionGroup);
        if (sampleCondition.equals("")) {
            showError("Please select a sample condition");
            return;
        }

        String complianceStatement = getSelectedRadioButtonText(complianceStatementGroup);
        if (complianceStatement.equals("")) {
            showError("Please select a compliance statement");
            return;
        }

        String standardDeviation = getSelectedRadioButtonText(standardDeviationGroup);
        if (standardDeviation.equals("")) {
            showError("Please select a standard deviation");
            return;
        }

        // Prepare data for Firestore in the correct order
        Map<String, Object> data = new HashMap<>();
        data.put("customerName", customerName);
        data.put("dispatchAddress", dispatchAddress);
        data.put("email", email);
        data.put("mobileNumber", mobileNumber);
        data.put("modeOfDispatch", modeOfDispatch);
        data.put("termsAndConditions", termsAndConditions);
        data.put("sampleCondition", sampleCondition);
        data.put("complianceStatement", complianceStatement);
        data.put("standardDeviation", standardDeviation);

        // Store data in Firestore
        db.collection("Total Orders")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(requireContext(), "Data submitted successfully", Toast.LENGTH_SHORT).show();
                    clearForm();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(requireContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show())
                .addOnCompleteListener(task -> {
                    // Re-enable the button and hide progress
                    isSubmitting = false;
                    submitButton.setEnabled(true);
                });
    }

    private String getSelectedRadioButtonText(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = radioGroup.findViewById(selectedId);
            return radioButton.getText().toString();
        }
        return "";
    }


    private void clearForm() {
        customerNameField.setText("");
        dispatchAddressField.setText("");
        mobileNumberField.setText("");
        emailField.setText("");
        termsAndConditionsField.setText("");
        for (int i = 0; i < pointsGroup.getChildCount(); i++) {
            View child = pointsGroup.getChildAt(i);
            if (child instanceof CheckBox) {
                ((CheckBox) child).setChecked(false);
            }
        }
        sampleConditionGroup.clearCheck();
        complianceStatementGroup.clearCheck();
        standardDeviationGroup.clearCheck();
    }

    private void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
        isSubmitting = false;
        submitButton.setEnabled(true);
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