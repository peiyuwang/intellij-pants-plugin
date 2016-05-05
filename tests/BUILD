NUM_TARGETS = 6
ALL_TEST_SOURCES = sorted(list(rglobs('*Test.java')))

TEST_NAMES = ['part_{}'.format(i) for i in xrange(NUM_TARGETS)]
TEST_SRCS = [ALL_TEST_SOURCES[i::NUM_TARGETS] for i in xrange(NUM_TARGETS)]

java_library(
  name = 'lib',
  dependencies = [
    '3rdparty/intellij:sdk',
    '3rdparty/intellij:python-plugin',
    '3rdparty/intellij:scala-plugin',
    'jps-plugin',
    'src:pants-intellij-plugin-scala',
    'testFramework:test-lib'
  ],
  sources = [
    'com/jetbrains/python/library/PythonLibraryType.java',
    'com/twitter/intellij/pants/completion/PantsCompletionTestBase.java',
    'com/twitter/intellij/pants/highlighting/PantsHighlightingIntegrationTestBase.java',
    'com/twitter/intellij/pants/service/project/PantsResolverTestBase.java',
  ],
)

for i in xrange(NUM_TARGETS):
  junit_tests(
    name = TEST_NAMES[i],
    dependencies = [
      ':lib',
      '3rdparty/intellij:sdk',
      '3rdparty/intellij:python-plugin',
      '3rdparty/intellij:scala-plugin',
      'jps-plugin',
      'src:pants-intellij-plugin-scala',
      'testFramework:test-lib'
    ],
    sources = TEST_SRCS[i],
  )

target(
  name = 'all',
  dependencies = [
    'tests:part_{}'.format(i) for i in xrange(NUM_TARGETS)
  ]
)