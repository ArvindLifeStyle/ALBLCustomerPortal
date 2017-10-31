package com.abc.portal.service;

import java.io.Serializable;
import java.sql.SQLException;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abc.portal.dao.CodeSequenceNumberGenerator;
import com.abc.portal.dao.DBCodeSequenceGenerator;

@Service
public class CodeGeneratorService {

	private final CodeSequenceNumberGenerator codeSequenceNumberGenerator;
	private final DBCodeSequenceGenerator dbCodeSequenceGenerator;

	public CodeGeneratorService(final CodeSequenceNumberGenerator codeSequenceNumberGenerator,
			final DBCodeSequenceGenerator dbCodeSequenceGenerator) {
		this.codeSequenceNumberGenerator = codeSequenceNumberGenerator;
		this.dbCodeSequenceGenerator = dbCodeSequenceGenerator;
	}

	@Transactional
	public String generate(final String sequenceName) {
		try {
			Serializable codeSequenceNumber;
			try {
				codeSequenceNumber = codeSequenceNumberGenerator.getNextSequence(sequenceName);
			} catch (final SQLGrammarException e) {
				codeSequenceNumber = dbCodeSequenceGenerator.createAndGetNextSequence(sequenceName);
			}

			return String.format("%d", codeSequenceNumber);
		} catch (final SQLException e) {
			throw new RuntimeException("Error occurred while generating Code", e);
		}
	}
}
