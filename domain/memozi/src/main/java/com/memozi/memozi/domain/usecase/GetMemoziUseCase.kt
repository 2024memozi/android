package com.memozi.memozi.domain.usecase

import com.memozi.memozi.domain.repository.MemoziRepository
import javax.inject.Inject

class GetMemoziUseCase @Inject constructor(
    private val memoziRepository: MemoziRepository
) {
    suspend operator fun invoke() = memoziRepository.getMemozi()
}
