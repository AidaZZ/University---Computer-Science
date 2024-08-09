# Additional clean files
cmake_minimum_required(VERSION 3.16)

if("${CONFIG}" STREQUAL "" OR "${CONFIG}" STREQUAL "Debug")
  file(REMOVE_RECURSE
  "CMakeFiles\\SP23_autogen.dir\\AutogenUsed.txt"
  "CMakeFiles\\SP23_autogen.dir\\ParseCache.txt"
  "SP23_autogen"
  )
endif()
