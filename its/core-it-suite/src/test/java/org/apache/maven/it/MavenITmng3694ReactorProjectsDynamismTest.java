package org.apache.maven.it;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;

import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

/**
 * Verify that any plugin injecting reactorProjects gets project instances that
 * have their concrete state calculated.
 * 
 * @author jdcasey
 */
public class MavenITmng3694ReactorProjectsDynamismTest
    extends AbstractMavenIntegrationTestCase
{
    public MavenITmng3694ReactorProjectsDynamismTest()
    {
        super( "[,2.99.99)" );
    }

    public void testitMNG3694 ()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/mng-3694" );

        File pluginDir = new File( testDir, "maven-mng3694-plugin" );
        File projectDir = new File( testDir, "projects" );
        
        Verifier verifier = new Verifier( pluginDir.getAbsolutePath() );

        verifier.executeGoal( "install" );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();
        
        verifier = new Verifier( projectDir.getAbsolutePath() );

        verifier.executeGoal( "validate" );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();
    }
}
