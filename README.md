# Insurance Claims Agent

## Description
A Spring Boot application that processes FNOL (First Notice of Loss) documents
in PDF and TXT formats, extracts key claim fields, validates mandatory data,
and routes claims for straight-through processing or manual review.

## Features
- Supports PDF and TXT FNOL documents
- Field extraction using pattern matching
- Mandatory field validation
- Claim routing logic
- Handles empty and partial documents gracefully

## How to Run
1. Open project in STS/IntelliJ Idea
2. Run InsuranceClaimsAgentApplication
3. Access:
   http://localhost:8080/claim/process

## Sample FNOL Files
Located in:
src/main/resources/data/

- fnol_1.pdf
- fnol_2.txt 
- fnol_3.txt
- fnol_4.txt
- fnol_empty.pdf

## Routing Logic
- Missing mandatory fields → Manual Review
- All mandatory fields present → Straight Through Processing
