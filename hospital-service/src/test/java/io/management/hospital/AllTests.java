package io.management.hospital;

import io.management.hospital.service.impl.AddressServiceImplTest;
import io.management.hospital.service.impl.DoctorServiceImplTest;
import io.management.hospital.service.impl.HospitalServiceImplTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("All Tests")
@SelectPackages("io.management.hospital.service.impl") // Selects all test classes in the specified package
@SelectClasses({
        AddressServiceImplTest.class,
        HospitalServiceImplTest.class,
        DoctorServiceImplTest.class
})
class AllTests {
}
