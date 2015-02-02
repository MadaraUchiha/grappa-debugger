/**
 * This class is generated by jOOQ
 */
package com.github.fge.grappa.debugger.db.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.1"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MatchersRecord extends org.jooq.impl.UpdatableRecordImpl<com.github.fge.grappa.debugger.db.tables.records.MatchersRecord> implements org.jooq.Record4<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String> {

	private static final long serialVersionUID = -879886812;

	/**
	 * Setter for <code>PUBLIC.MATCHERS.ID</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>PUBLIC.MATCHERS.ID</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>PUBLIC.MATCHERS.CLASS_NAME</code>.
	 */
	public void setClassName(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>PUBLIC.MATCHERS.CLASS_NAME</code>.
	 */
	public java.lang.String getClassName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>PUBLIC.MATCHERS.MATCHER_TYPE</code>.
	 */
	public void setMatcherType(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>PUBLIC.MATCHERS.MATCHER_TYPE</code>.
	 */
	public java.lang.String getMatcherType() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>PUBLIC.MATCHERS.NAME</code>.
	 */
	public void setName(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>PUBLIC.MATCHERS.NAME</code>.
	 */
	public java.lang.String getName() {
		return (java.lang.String) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return com.github.fge.grappa.debugger.db.tables.Matchers.MATCHERS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return com.github.fge.grappa.debugger.db.tables.Matchers.MATCHERS.CLASS_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return com.github.fge.grappa.debugger.db.tables.Matchers.MATCHERS.MATCHER_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return com.github.fge.grappa.debugger.db.tables.Matchers.MATCHERS.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getClassName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getMatcherType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MatchersRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MatchersRecord value2(java.lang.String value) {
		setClassName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MatchersRecord value3(java.lang.String value) {
		setMatcherType(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MatchersRecord value4(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MatchersRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.String value3, java.lang.String value4) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached MatchersRecord
	 */
	public MatchersRecord() {
		super(com.github.fge.grappa.debugger.db.tables.Matchers.MATCHERS);
	}

	/**
	 * Create a detached, initialised MatchersRecord
	 */
	public MatchersRecord(java.lang.Integer id, java.lang.String className, java.lang.String matcherType, java.lang.String name) {
		super(com.github.fge.grappa.debugger.db.tables.Matchers.MATCHERS);

		setValue(0, id);
		setValue(1, className);
		setValue(2, matcherType);
		setValue(3, name);
	}
}